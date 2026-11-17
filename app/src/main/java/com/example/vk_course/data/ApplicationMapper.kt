package com.example.vk_course.data

import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.data.remote.ApplicationApiDTO
import com.example.vk_course.data.remote.ApplicationApiListItemDTO
import com.example.vk_course.domain.model.AgeRating
import com.example.vk_course.domain.model.Application
import com.example.vk_course.domain.model.toCategory

object ApplicationMapper {
    fun toDomain(dto: ApplicationApiDTO): Application {
        return Application(
            id = dto.id,
            iconUrl = dto.iconUrl,
            name = dto.name,
            description = dto.description,
            category = dto.category.toCategory(),
            developer = dto.developer,
            ageRating = if (dto.ageRating >= 12) AgeRating.ADULTS else AgeRating.CHILDREN,
            screenshotUrlList = dto.screenshotUrlList,
            size = dto.size,
            rating = null
        )
    }

    fun toDomain(dto: ApplicationApiListItemDTO): Application {
        return Application(
            id = dto.id,
            iconUrl = dto.iconUrl,
            name = dto.name,
            description = dto.description,
            category = dto.category.toCategory(),
            developer = null,
            ageRating = null,
            screenshotUrlList = null,
            size = null,
            rating = null
        )
    }

    fun toDB(domain:Application) : ApplicationDTO {
        return ApplicationDTO(
            id = domain.id,
            iconUrl = domain.iconUrl,
            name = domain.name,
            description =domain.description,
            category = domain.category,
            developer = domain.developer,
            ageRating = domain.ageRating,
            screenshotUrlList = domain.screenshotUrlList,
            size = domain.size
        )
    }
}