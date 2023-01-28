package com.sarrawi.msgs.repository

import android.util.Log
import com.sarrawi.msgs.api.ApiService
import com.sarrawi.msgs.db.LocaleSource
import com.sarrawi.msgs.models.MsgsTypesModel
import com.sarrawi.msgs.models.MsgsTypesResponse
import retrofit2.Response

class MsgsTypesRepo
//@Inject
constructor(private val apiService: ApiService, private val localSource: LocaleSource) {
    suspend fun getMsgsTypes_Ser() = apiService.getMsgsTypes_Ser()

    suspend fun getLocalPosts() = localSource.getLocalPosts()

    suspend fun insertPosts(posts: List<MsgsTypesModel>?) {
        if (!posts.isNullOrEmpty()) {
            localSource.insertPosts(posts)
        }
    }
}