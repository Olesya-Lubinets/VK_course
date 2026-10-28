package com.example.vk_course.data

import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.domain.model.AgeRating
import com.example.vk_course.domain.model.Application

object ApplicationMapper {
    fun toDomain(dto: ApplicationDTO):Application {
      return   Application(
            id = dto.id,
            iconUrl = dto.iconUrl,
            name = dto.name,
            description = dto.description,
            category = dto.category,
            developer = dto.developer,
            ageRating = if (dto.ageRating>=12) AgeRating.ADULTS else AgeRating.CHILDREN,
            screenshotUrlList = dto.screenshotUrlList,
            size = dto.size
        )
    }
}