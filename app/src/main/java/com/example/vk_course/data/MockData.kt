package com.example.vk_course.data

import com.example.vk_course.domain.Category
import com.example.vk_course.domain.VKApplication

object  MockData {
    val applications = listOf(
        VKApplication(
            icon = "https://cdn.simpleicons.org/spotify",
            title = "Spotify",
            description = "Слушайте музыку, открывайте новых исполнителей и создавайте собственные плейлисты.",
            category = Category.MEDIA
        ),
        VKApplication(
            icon = "https://cdn.simpleicons.org/telegram",
            title = "Telegram",
            description = "Общайтесь с друзьями, отправляйте файлы и создавайте групповые чаты и каналы.",
            category = Category.SOCIAL
        ),
        VKApplication(
            icon = "https://cdn.simpleicons.org/notion",
            title = "Notion",
            description = "Организуйте задачи, ведите заметки и планируйте проекты в одном пространстве.",
            category = Category.PRODUCTIVITY
        ),
        VKApplication(
            icon = "https://cdn.simpleicons.org/monzo",
            title = "Monzo",
            description = "Управляйте личными финансами, отслеживайте расходы и контролируйте бюджет.",
            category = Category.FINANCE
        ),
        VKApplication(
            icon = "https://icons.iconarchive.com/icons/papirus-team/papirus-apps/128/minecraft-icon.png",
            title = "Minecraft",
            description = "Исследуйте открытый мир, собирайте ресурсы и создавайте собственные постройки.",
            category = Category.GAME
        )
    )
}