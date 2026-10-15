package com.example.vk_course.data


import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vk_course.domain.VKApplication
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao

@Dao
interface ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(VKApplication: VKApplication)

    @Query("SELECT * FROM applications")
    fun getAll(): Flow<List<VKApplication>>
}
