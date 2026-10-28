package com.example.vk_course.data.local


import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao

@Dao
interface ApplicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(Application: ApplicationDTO)

    @Query("SELECT * FROM applications")
    fun getAll(): Flow<List<ApplicationDTO>>

    @Query("SELECT * FROM applications WHERE id = :id")
    suspend fun getByID(id:Int): ApplicationDTO?

    @Query("SELECT COUNT(*) FROM applications")
    suspend fun getApplicationsCount():Int
}
