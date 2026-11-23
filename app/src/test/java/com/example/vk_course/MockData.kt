package com.example.vk_course

import com.example.vk_course.data.remote.ApplicationApiDTO
import com.example.vk_course.data.remote.ApplicationApiListItemDTO
import com.example.vk_course.domain.model.AgeRating
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.model.Category
import com.example.vk_course.domain.model.toCategory

object MockData {
    val testAppListFromAPI =  listOf(
        ApplicationApiListItemDTO(
            id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
            iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
            name = "QuickNote",
            description = "Лёгкое приложение для заметок и напоминаний",
            category = "Производительность"
        ),
        ApplicationApiListItemDTO(
            id = "82ea6f63-2b2a-43e1-b54c-ec62e6e27229",
            iconUrl = "https://fastly.picsum.photos/id/238/200/200.jpg?hmac=O4Jc6lqHVfaKVzLf8bWssNTbWzQoaRUC0TDXod9xDdM",
            name = "Calmalist",
            description = "Списки дел с расслабляющими напоминаниями",
            category = "Производительность"
        ),
        ApplicationApiListItemDTO(
            id = "c6f64035-c432-4aad-a550-a1dac2d3b047",
            iconUrl = "https://fastly.picsum.photos/id/239/200/200.jpg?hmac=8JqlXUpZ9Xy0H6tMK8sCPQAYU9vUn9Qa8Kg-U9h3sCY",
            name = "FitRiver",
            description = "Трекер прогулок и пробежек",
            category = "Здоровье и фитнес"
        ),
        ApplicationApiListItemDTO(
            id = "d22b7cda-2d36-4ebd-bfa3-be58770fa455",
            iconUrl = "https://fastly.picsum.photos/id/240/200/200.jpg?hmac=PZiJibMUuv5Zpv9MAcqLaCYJOPt-XNuDh3RPBZiGcoY",
            name = "TaskLoop",
            description = "Управление повседневными задачами",
            category = "Производительность"
        ),
        ApplicationApiListItemDTO(
            id = "f830e414-42a9-4fe6-af16-555a85f6f6c2",
            iconUrl = "https://fastly.picsum.photos/id/241/200/200.jpg?hmac=F-mDYyK1xUiXjlLd5PvE8EQpTwGfFndV7mMaANDtl28",
            name = "Pixtrix",
            description = "Фоторедактор с фильтрами",
            category = "Фото и видео"
        ),
        ApplicationApiListItemDTO(
            id = "ba31ce5d-b214-46e9-bf7c-35e99e70af14",
            iconUrl = "https://fastly.picsum.photos/id/242/200/200.jpg?hmac=Z3aa8zbEQkEMFgnVh0Pn96vmCZHhJ17qzCrePYksrcY",
            name = "TinyChef",
            description = "Быстрые рецепты и планирование покупок",
            category = "Еда и напитки"
        ),
        ApplicationApiListItemDTO(
            id = "204a939c-39fb-4c9d-bed9-daeeba8a465e",
            iconUrl = "https://fastly.picsum.photos/id/243/200/200.jpg?hmac=fW5ZwzzyTBy2t2MROp988oq12mZnKwN0coFLhZEE87s",
            name = "GymMate",
            description = "Дневник тренировок и прогресса",
            category = "Здоровье и фитнес"
        ))

  val testAppItemFromAPI =  ApplicationApiDTO(
        id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
        iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
        name = "QuickNote",
        description = "QuickNote — практичное приложение для хранения, организации и быстрой фиксации идей. Можно создавать цветные блокноты и добавлять напоминания на даты. Благодаря поддержке синхронизации заметки доступны на всех устройствах. Быстрый поиск, простая организация по категориям, защита паролем, экспорт в облако и дружественный интерфейс делают решение незаменимым помощником для любого пользователя. Даже длинные списки дел легко разобрать по тегам и статусу.",
        category = "Производительность",
        developer = "QuickSoft Inc.",
        ageRating = 3,
        size = 8.2f,
        screenshotUrlList = listOf(
            "https://fastly.picsum.photos/id/2/200/200.jpg?hmac=isSWZUpv7D1D156XcADPOCZlfCG9mmvb8OlXFdvLdK0",
            "https://fastly.picsum.photos/id/3/200/200.jpg?hmac=N5yYUNYl5gOUcaMmTtnNNtx839TN2qaNM4SaXhQl65U",
            "https://fastly.picsum.photos/id/4/200/200.jpg?hmac=ozRrjh7SMobdmKcOU0f5sWYszFW9Or1c90qeq7uqtuw",
            "https://fastly.picsum.photos/id/5/200/200.jpg?hmac=oN9VtXdJYLSFssji8vCr48JaI-e5Zi4eH9GAiYBB_Ig",
            "https://fastly.picsum.photos/id/6/200/200.jpg?hmac=g4Q9Vcu5Ohm8Rwap3b6HSIxUfIALZ6BasESHhw7VjLE"
        )
    )

    val testApplication =  Application(
        id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
        iconUrl = "https://fastly.picsum.photos/id/237/200/200.jpg?hmac=zHUGikXUDyLCCmvyww1izLK3R3k8oRYBRiTizZEdyfI",
        name = "QuickNote",
        description = "QuickNote — практичное приложение для хранения, организации и быстрой фиксации идей. Можно создавать цветные блокноты и добавлять напоминания на даты. Благодаря поддержке синхронизации заметки доступны на всех устройствах. Быстрый поиск, простая организация по категориям, защита паролем, экспорт в облако и дружественный интерфейс делают решение незаменимым помощником для любого пользователя. Даже длинные списки дел легко разобрать по тегам и статусу.",
        category = Category.PRODUCTIVITY,
        developer = "QuickSoft Inc.",
        ageRating = AgeRating.CHILDREN,
        size = 8.2f,
        rating = 4.5f,
        screenshotUrlList = listOf(
            "https://fastly.picsum.photos/id/2/200/200.jpg?hmac=isSWZUpv7D1D156XcADPOCZlfCG9mmvb8OlXFdvLdK0",
            "https://fastly.picsum.photos/id/3/200/200.jpg?hmac=N5yYUNYl5gOUcaMmTtnNNtx839TN2qaNM4SaXhQl65U",
            "https://fastly.picsum.photos/id/4/200/200.jpg?hmac=ozRrjh7SMobdmKcOU0f5sWYszFW9Or1c90qeq7uqtuw",
            "https://fastly.picsum.photos/id/5/200/200.jpg?hmac=oN9VtXdJYLSFssji8vCr48JaI-e5Zi4eH9GAiYBB_Ig",
            "https://fastly.picsum.photos/id/6/200/200.jpg?hmac=g4Q9Vcu5Ohm8Rwap3b6HSIxUfIALZ6BasESHhw7VjLE"
        )
    )
}
