package com.sarrawi.msgs.db

import android.content.Context
import com.sarrawi.msgs.db.Dao.MsgsTypesDao
import com.sarrawi.msgs.models.MsgsTypesModel

class LocaleSource(context: Context) {

    private var postsDao: MsgsTypesDao?

    init {
        val dataBase = PostDatabas.getInstance(context.applicationContext)
        postsDao = dataBase.postsDao()
    }

    companion object {
        private var sourceConcreteClass: LocaleSource? = null
        fun getInstance(context: Context): LocaleSource {
            if (sourceConcreteClass == null)
                sourceConcreteClass = LocaleSource(context)
            return sourceConcreteClass as LocaleSource
        }
    }

    suspend fun getLocalPosts(): List<MsgsTypesModel> {
        return postsDao?.getLocalPosts()!!
    }

    suspend fun insertPosts(posts: List<MsgsTypesModel>) {
         postsDao?.insertPosts(posts)!!
    }

    suspend fun deletePosts() {
         postsDao?.deleteALlPosts()
    }

}