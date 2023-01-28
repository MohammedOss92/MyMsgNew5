package com.sarrawi.msgs.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarrawi.msgs.models.MsgsTypesModel
import com.sarrawi.msgs.repository.MsgsTypesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//@HiltViewModel
class MsgsTypesViewModel
//@Inject
constructor(private val msgsTypesRepo: MsgsTypesRepo) : ViewModel() {

    private val _response = MutableLiveData<List<MsgsTypesModel>>()

    val responseMsgsTypes: LiveData<List<MsgsTypesModel>>
        get() = _response

    init {
        //   getAllMsgsTypes()
        getPostsFromRoom()
    }

    private fun getAllMsgsTypes() = viewModelScope.launch {
        msgsTypesRepo.getMsgsTypes_Ser().let { response ->
            Log.d("sww", "dfrr")
            Log.d("sww", "" + response.body())
            if (response.isSuccessful) {
                // sweilem edit
                Log.i("TestRoom", "getAllMsgsTypes: data returned successful")
                _response.postValue(response.body()?.results)
                Log.i("TestRoom", "getAllMsgsTypes: posts ${response.body()?.results}")
                //here get data from api so will insert it to local database
                msgsTypesRepo.insertPosts(response.body()?.results)
            } else {
                Log.i("TestRoom", "getAllMsgsTypes: data corrupted")
                Log.d("tag", "getAll Error: ${response.code()}")
            }
        }
    }

    private fun getPostsFromRoom() {
        viewModelScope.launch {
            val response = msgsTypesRepo.getLocalPosts()
            withContext(Dispatchers.Main) {
                if (response.isEmpty()) {
                    Log.i("TestRoom", "getPostsFromRoom: will cal api")
                    //no data in database so will call data from API
                    getAllMsgsTypes()
                } else {
                    Log.i("TestRoom", "getPostsFromRoom: get from Room")
                    _response.postValue(response)
                }
            }
        }
    }

    fun refreshPosts() {
        getAllMsgsTypes()
    }
}




