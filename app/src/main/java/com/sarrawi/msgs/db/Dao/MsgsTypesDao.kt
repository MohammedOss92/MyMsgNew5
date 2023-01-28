package com.sarrawi.msgs.db.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sarrawi.msgs.models.MsgsTypesModel

@Dao
interface MsgsTypesDao {

    @Query("SELECT * FROM msg_types_table")
    fun getPosts(): LiveData<List<MsgsTypesModel>>

    @Query("SELECT * FROM msg_types_table")
    fun getPosts2():  List<MsgsTypesModel>

    // get last id to compare with server data
    @Query("SELECT * FROM msg_types_table ORDER BY id DESC LIMIT 1")
    fun getLastId(): MsgsTypesModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(msgsTypesModels: MsgsTypesModel)

    @Update
    suspend fun update(note: MsgsTypesModel)

    @Delete
    suspend fun delete(note: MsgsTypesModel)
}