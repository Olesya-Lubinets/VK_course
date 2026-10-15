package com.example.vk_course

import com.example.vk_course.domain.VKApplication

sealed class DBState {
    data object Error: DBState()
    data object Loading: DBState()
    data class Success(val vkApplicationList:List<VKApplication>): DBState()
}