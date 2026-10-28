package com.example.vk_course.data

import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.domain.model.Category

object MockData {
    val mockApplications = listOf(
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fspotify%2F1DB954&output=png",
            name = "Spotify",
            description = "Музыкальный сервис для прослушивания песен, альбомов и подкастов.",
            category = Category.MEDIA,
            developer = "Spotify AB",
            ageRating = 12,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1516280440614-37939bbacd81",
                "https://images.unsplash.com/photo-1511379938547-c1f69419868d"
            ),
            size = 85.5f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Ftelegram%2F26A5E4&output=png",
            name = "Telegram",
            description = "Мессенджер для общения, групповых чатов, каналов и обмена файлами.",
            category = Category.SOCIAL,
            developer = "Telegram FZ-LLC",
            ageRating = 12,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1611746872915-64382b5c76da",
                "https://images.unsplash.com/photo-1611162616475-46b635cb6868"
            ),
            size = 42.3f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Finstagram%2FE4405F&output=png",
            name = "Instagram",
            description = "Социальная сеть для публикации фотографий, видео и историй.",
            category = Category.SOCIAL,
            developer = "Instagram",
            ageRating = 12,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1534528741775-53994a69daeb",
                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32"
            ),
            size = 120.0f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fnotion%2F000000&output=png",
            name = "Notion",
            description = "Рабочее пространство для заметок, документов, задач и управления проектами.",
            category = Category.PRODUCTIVITY,
            developer = "Notion Labs, Inc.",
            ageRating = 3,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1456324504439-367cee3b3c32",
                "https://images.unsplash.com/photo-1499951360447-b19be8fe80f5"
            ),
            size = 65.2f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fyoutube%2FFFF000&output=png",
            name = "YouTube",
            description = "Видеоплатформа с развлекательным контентом, обучающими роликами и трансляциями.",
            category = Category.MEDIA,
            developer = "Google LLC",
            ageRating = 12,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1492619375914-88005aa9e8fb",
                "https://images.unsplash.com/photo-1498050108023-c5249f4df085"
            ),
            size = 110.7f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fduolingo%2F58CC02&output=png",
            name = "Duolingo",
            description = "Приложение для изучения иностранных языков с короткими интерактивными уроками.",
            category = Category.LIFESTYLE,
            developer = "Duolingo, Inc.",
            ageRating = 3,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1455390582262-044cdead277a",
                "https://images.unsplash.com/photo-1434030216411-0b793f4b4173"
            ),
            size = 78.4f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fmonzo%2F14233C&output=png",
            name = "Monzo",
            description = "Мобильный банк для управления счетами, расходами и повседневными финансами.",
            category = Category.FINANCE,
            developer = "Monzo Bank Limited",
            ageRating = 3,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1554224155-6726b3ff858f",
                "https://images.unsplash.com/photo-1460925895917-afdab827c52f"
            ),
            size = 95.6f
        ),
        ApplicationDTO(
            iconUrl = "https://pngimg.com/uploads/angry_birds/angry_birds_PNG11.png",
            name = "Angry Birds",
            description = "Аркадная игра, в которой нужно запускать птиц из рогатки и разрушать конструкции.",
            category = Category.GAME,
            developer = "Rovio Entertainment",
            ageRating = 3,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1511512578047-dfb367046420",
                "https://images.unsplash.com/photo-1493711662062-fa541adb3fc8"
            ),
            size = 150.2f
        ),
        ApplicationDTO(
            iconUrl = "https://images.weserv.nl/?url=cdn.simpleicons.org%2Fgooglecalendar%2F4285F4&output=png",
            name = "Google Calendar",
            description = "Календарь для планирования встреч, событий и повседневных задач.",
            category = Category.PRODUCTIVITY,
            developer = "Google LLC",
            ageRating = 3,
            screenshotUrlList = listOf(
                "https://images.unsplash.com/photo-1506784983877-45594efa4cbe",
                "https://images.unsplash.com/photo-1506784365847-bbad939e9335"
            ),
            size = 32.8f
        )
    )
}
