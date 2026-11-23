package com.example.vk_course

import com.example.vk_course.data.ApplicationMapper
import com.example.vk_course.data.local.ApplicationDao
import com.example.vk_course.data.remote.ApplicationApiDTO
import com.example.vk_course.data.remote.ApplicationApiListItemDTO
import com.example.vk_course.data.remote.AppsAPIService
import com.example.vk_course.data.repository.ApplicationRepositoryImpl
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.repository.ApplicationRepository
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import kotlin.math.max
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first


class ApplicationRepositoryImplTest {

    private lateinit var dao: ApplicationDao
    private lateinit var api: AppsAPIService
    private lateinit var repository: ApplicationRepository

    @Before
    fun setup() {
        dao = mockk<ApplicationDao>()
        api = mockk<AppsAPIService>()
        repository = ApplicationRepositoryImpl(api,dao)
    }

    @Test
    fun `getAllApplications return correct List of Application`() = runTest{
        coEvery { api.getAllApps() } returns MockData.testAppListFromAPI
        val expected =  MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) }

        val result = repository.getAllApplications().first()
        assertEquals(expected,result)
    }

    @Test
    fun `getAllApplications calls api getAll ones`() = runTest {
        coEvery { api.getAllApps() } returns emptyList<ApplicationApiListItemDTO>()
        repository.getAllApplications().first()
        coVerify(exactly = 1) { api.getAllApps() }
    }

    @Test
    fun `getAllApplications returns empty list when Api returns empty list`() = runTest {
        coEvery { api.getAllApps() } returns emptyList()
        val expected = emptyList<ApplicationApiDTO>()
        val result = repository.getAllApplications().first()
        assertEquals(expected,result)
    }

    @Test
    fun `getVKApplicationByID returns correct Application with Id`() = runTest {
        val id = "fa2e31b8-1234-4cf7-9914-108a170a1b01"
        val appMockItem = MockData.testAppItemFromAPI
        coEvery { api.getApplication(id) } returns appMockItem
        val result = repository.getVKApplicationByID(id)
        val expected = ApplicationMapper.toDomain(appMockItem)
        assertEquals(expected,result)
    }

    @Test
    fun `getVKApplicationByID returns null if Api returns null`() = runTest {
        coEvery { api.getApplication(any())} returns null
        assertNull(repository.getVKApplicationByID(""))
    }


    @Test
    fun `addApplicationToDB() calls  dbDao insert with correct argument`() = runTest {
        val testApplication = MockData.testApplication
        val testApplicationDTO = ApplicationMapper.toDB(testApplication)
        coEvery { dao.insert(testApplicationDTO) } just Runs
        repository.addApplicationToDB(testApplication)
        coVerify (exactly = 1) { dao.insert(testApplicationDTO) }
    }

    @Test
    fun `deleteApplicationFromDB calls dbDAO delete with correct argument`() = runTest {
        val testApplication = MockData.testApplication
        val testApplicationID = testApplication.id
        coEvery { dao.deleteAppById(testApplicationID) } just Runs
        repository.deleteApplicationFromDB(testApplication)
        coVerify (exactly = 1) { dao.deleteAppById(testApplicationID) }
    }

    @Test
    fun getRating() = runTest {
        val result = repository.getRating("fa2e31b8-1234-4cf7-9914-108a170a1b01")
        assertTrue( (result in  3.0f..5.0f ))
    }

    @Test
    fun `loadRatingsSequentially gets correct List of Pair of Id to Raiting`() = runTest{
        val appList: List<Application> = MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) }
        val appIdsList = appList.map { it.id }
        val spyRepository = spyk(repository)
        coEvery { spyRepository.getRating(any()) } returns 3.5f
        val result = spyRepository.loadRatingsSequentially(appIdsList)
        val expected = appIdsList.map { it to 3.5f }
        assertEquals(expected,result)

    }

    @Test
    fun `loadRatingsInParallel gets correct List of Pair of Id to Raiting`() = runTest {
        val appList: List<Application> = MockData.testAppListFromAPI.map { ApplicationMapper.toDomain(it) }
        val appIdsList = appList.map { it.id }
        val spyRepository = spyk(repository)
        coEvery { spyRepository.getRating(any()) } returns 3.5f
        val result = spyRepository.loadRatingsInParallel(appIdsList)
        val expected = appIdsList.map { it to 3.5f }
        assertEquals(expected,result)
    }

    @Test
    fun `loadRatingsSequentially expects calls one by one`() = runTest {
        val spyRepository = spyk(repository)
        var activeCalls:Int = 0
        var maxCallsInSameTime = 0

        coEvery { spyRepository.getRating(any()) } coAnswers {
            activeCalls += 1
            maxCallsInSameTime = max(activeCalls,maxCallsInSameTime)
            delay(1500L)
            activeCalls-=1
            3.5f
        }
        spyRepository.loadRatingsSequentially(MockData.testAppListFromAPI.map { it.id })
        assertEquals(maxCallsInSameTime,1)
    }

    @Test
    fun `loadRatingsInParallel expects calls in parallel`() = runTest {
        val spyRepository = spyk(repository)
        var activeCalls = 0
        var maxCallsInSameTime = 0
        coEvery { spyRepository.getRating(any()) } coAnswers {
            activeCalls += 1
            maxCallsInSameTime = max(activeCalls,maxCallsInSameTime)
            delay(1500L)
            activeCalls-=1
         3.5f
        }
        spyRepository.loadRatingsInParallel(MockData.testAppListFromAPI.map { it.id })
        assertTrue(maxCallsInSameTime>1)
    }

    @Test
    fun `loadRatingsInParallel calls getRating list size times`() = runTest {
        val spyRepository = spyk(repository)
        var calls = 0
        coEvery { spyRepository.getRating(any()) } coAnswers {
            calls += 1
            3.5f
        }
        val appsList = MockData.testAppListFromAPI
        spyRepository.loadRatingsInParallel(appsList.map { it.id })
        assertEquals(appsList.size,calls)
    }
}