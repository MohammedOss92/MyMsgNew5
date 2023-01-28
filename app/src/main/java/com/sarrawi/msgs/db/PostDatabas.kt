package com.sarrawi.msgs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sarrawi.msgs.db.Dao.MsgsTypesDao
import com.sarrawi.msgs.models.MsgsTypesModel

@Database(entities = [MsgsTypesModel::class], version = 3, exportSchema = false)
abstract class PostDatabas : RoomDatabase() {

    abstract fun postsDao(): MsgsTypesDao
    companion object{

        @Volatile
        private var instance: PostDatabas? = null

        fun getInstance(con: Context):PostDatabas{
            if (instance==null){
                instance= Room.databaseBuilder(con,PostDatabas::class.java,"PostDatabas")

                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)


                        }
                    })

                    .build()

            }
            return instance!!
        }
    }
}