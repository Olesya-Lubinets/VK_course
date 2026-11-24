package com.example.vk_course

import android.database.sqlite.SQLiteException
import com.example.vk_course.data.ApplicationMapper
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.presentation.AppDetailsState
import com.example.vk_course.presentation.AppListState
import com.example.vk_course.presentation.ApplicationViewModel
import com.example.vk_course.presentation.DownloadEvent
import com.example.vk_course.presentation.appDetailsScreen.InstallState
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ApplicationViewModelTest {

    lateinit var viewModel: ApplicationViewModel
    lateinit var repository: ApplicationRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk<ApplicationRepository>()
        every { repository.getAllApplications() } returns  flowOf(MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) })
        viewModel = ApplicationViewModel(repository)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `listOfApplications return AppListState Success if repository returns list of apps` () = runTest {
        val mockApplicationList = MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) }
        val listWithRating = mockApplicationList.map { it.id to 3.5f }
        coEvery { repository.loadRatingsInParallel(mockApplicationList.map { it.id })
        }  returns listWithRating
        val mapRating = listWithRating.toMap()
        val expectedAppList = mockApplicationList.map { it.copy(rating = mapRating[it.id])}
        viewModel.listOfApplications.first()
        advanceUntilIdle()
        assertEquals(AppListState.Success(expectedAppList),viewModel.listOfApplications.value)
    }

    @Test
    fun `listOfApplications return AppListState Error if repository throws exception` () = runTest {
        val mockApplicationList = MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) }
        coEvery { repository.loadRatingsInParallel(mockApplicationList.map { it.id })
        }  throws Exception()
        viewModel.listOfApplications.first()
        advanceUntilIdle()
        assertEquals(AppListState.Error,viewModel.listOfApplications.value)
    }

    @Test
    fun `getById set AppDetailsState to Error if repository getVKApplicationByID returns null`() =
        runTest {
            coEvery { repository.getVKApplicationByID(any()) } returns null
            viewModel.getById("")
            assertEquals(AppDetailsState.Error, viewModel.appByID.value)
        }

    @Test
    fun `getById set AppDetailsState to Error if repository getVKApplicationByID  throws exception`() =
        runTest {
            coEvery { repository.getVKApplicationByID(any()) } throws Exception()
            viewModel.getById("")
            assertEquals(AppDetailsState.Error, viewModel.appByID.value)
        }

    @Test
    fun `getById set AppDetailsState to Content if repository getVKApplicationByID returns app`() =
        runTest {
            val mockApp = MockData.testApplication
            coEvery { repository.getVKApplicationByID(any()) } returns mockApp
            val expected = AppDetailsState.Content(mockApp, InstallState.NotInstalled)
            viewModel.getById("")
            assertEquals(expected, viewModel.appByID.value)
        }

    private suspend fun TestScope.beforeOnInstallClickTests(app: Application) {
        // This function sets app AppDetailsState to AppDetailsState.Content(app, InstallState.NotInstalled)
        coEvery {
            repository.getVKApplicationByID(app.id)
        } returns app
        viewModel.getById(app.id)
        advanceUntilIdle()
    }

    @Test
    fun `onInstallClick starts installation`() = runTest {
        val app = MockData.testApplication
        beforeOnInstallClickTests(app)
        coEvery { repository.addApplicationToDB(app) } just Runs
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        assertEquals(AppDetailsState.Content(app, InstallState.Installed), viewModel.appByID.value)
    }

    @Test
    fun `onInstallClick calls repository addApplicationToDB ones if NotInstalled`() = runTest {
        val app = MockData.testApplication
        beforeOnInstallClickTests(app)
        coEvery { repository.addApplicationToDB(app) } just Runs
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        coVerify(exactly = 1) { repository.addApplicationToDB(app) }
    }

    @Test
    fun `onInstallClick doesn't change AppDetailsState when appByID is not Content`() = runTest {
        val app = MockData.testApplication
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        assertEquals(AppDetailsState.Loading, viewModel.appByID.value)

    }

    @Test
    fun `onInstallClick dosn't add app to DB when appByID is not Content`() = runTest {
        val app = MockData.testApplication
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        coVerify(exactly = 0) { repository.addApplicationToDB(app) }
    }

    @Test
    fun `onInstallClick handle SQLiteException exception`() = runTest {
        val app = MockData.testApplication
        beforeOnInstallClickTests(app)
        coEvery { repository.addApplicationToDB(app) } throws SQLiteException()
        viewModel.onInstallClick(app)
        assertEquals(
            AppDetailsState.Content(app, InstallState.NotInstalled),
            viewModel.appByID.value
        )
    }

    @Test
    fun `onInstallClick sends downloadEvent Success if app installed`() = runTest {
        val app = MockData.testApplication
        beforeOnInstallClickTests(app)
        coEvery { repository.addApplicationToDB(app) } just Runs
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        val result = viewModel.downloadEvent.first()
        assertEquals(DownloadEvent.Success("Успешная загрузка"),result)
    }

    @Test
    fun `onInstallClick sends downloadEvent Error if repository throws exception`() = runTest {
        val app = MockData.testApplication
        beforeOnInstallClickTests(app)
        coEvery { repository.addApplicationToDB(app) }  throws SQLiteException()
        viewModel.onInstallClick(app)
        advanceUntilIdle()
        val result = viewModel.downloadEvent.first()
        assertEquals(DownloadEvent.Error("Неуспешная загрузка"),result)
    }
}