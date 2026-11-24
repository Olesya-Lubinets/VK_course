package com.example.vk_course

import com.example.vk_course.data.ApplicationMapper
import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.domain.model.AgeRating
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.model.Category
import org.junit.Test
import org.junit.Assert.*

class ApplicationMapperTest {

    @Test
    fun `toDomain maps ApplicationApiListItemDTO correctly`() {
        val apiDTO = MockData.testAppListFromAPI.first()
        val expectedDomain = Application(
            id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
            iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
            name = "QuickNote",
            description = "Лёгкое приложение для заметок и напоминаний",
            category = Category.PRODUCTIVITY,
            developer = null,
            ageRating = null,
            screenshotUrlList = null,
            size = null,
            rating = null
        )
        assertEquals(expectedDomain, ApplicationMapper.toDomain(apiDTO))
    }

    @Test
    fun `API DTO to domain AgeRating Children`() {
        val apiDTO = MockData.testAppItemFromAPIChildren
        assertEquals(AgeRating.CHILDREN, ApplicationMapper.toDomain(apiDTO).ageRating)
    }

    @Test
    fun `API DTO to domain AgeRating Adults`() {
        val apiDTO = MockData.testAppItemFromAPIAdults
        assertEquals(AgeRating.ADULTS, ApplicationMapper.toDomain(apiDTO).ageRating)
    }

    @Test
    fun `API DTO to domain category Productivity`() {
        val apiDTO = MockData.testAppItemFromAPIAdults
        assertEquals(Category.PRODUCTIVITY, ApplicationMapper.toDomain(apiDTO).category)
    }

    @Test
    fun `domain to database ApplicationDTO`() {
        val domain = MockData.testApplication
        val expectedDTO = ApplicationDTO(
            id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
            iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
            name = "QuickNote",
            description = "QuickNote — практичное приложение для хранения, организации и быстрой фиксации идей. Можно создавать цветные блокноты и добавлять напоминания на даты. Благодаря поддержке синхронизации заметки доступны на всех устройствах. Быстрый поиск, простая организация по категориям, защита паролем, экспорт в облако и дружественный интерфейс делают решение незаменимым помощником для любого пользователя. Даже длинные списки дел легко разобрать по тегам и статусу.",
            category = Category.PRODUCTIVITY,
            developer = "QuickSoft Inc.",
            ageRating = AgeRating.CHILDREN,
            size = 8.2f,
            screenshotUrlList = listOf(
                "https://fastly.picsum.photos/id/2/200/200.jpg?hmac=isSWZUpv7D1D156XcADPOCZlfCG9mmvb8OlXFdvLdK0",
                "https://fastly.picsum.photos/id/3/200/200.jpg?hmac=N5yYUNYl5gOUcaMmTtnNNtx839TN2qaNM4SaXhQl65U"
            )
        )
        assertEquals(expectedDTO, ApplicationMapper.toDB(domain))
    }
}