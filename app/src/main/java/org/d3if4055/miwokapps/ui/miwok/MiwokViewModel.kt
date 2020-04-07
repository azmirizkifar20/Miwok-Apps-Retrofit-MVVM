package org.d3if4055.miwokapps.ui.miwok

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.database.getDatabase
import org.d3if4055.miwokapps.network.MiwokApi
import org.d3if4055.miwokapps.repository.MiwokRepository

@Suppress("SpellCheckingInspection")
class MiwokViewModel(application: Application) : ViewModel() {

//    private val _loading = MutableLiveData<Boolean>()
//    val loading : LiveData<Boolean>
//        get() = _loading
//
//    private val _data = MutableLiveData<List<Miwok>>()
//    val data : LiveData<List<Miwok>>
//        get() = _data
//
//    private val _response = MutableLiveData<String>()
//    val response : LiveData<String>
//        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val database = getDatabase(application)
    private val miwokRepository = MiwokRepository(database)

    init {
        uiScope.launch {
            miwokRepository.refreshMiwokList()
        }
    }

    val listMiwok = miwokRepository.miwokList

//    private fun initData() {
//        _loading.value = true
//
//        uiScope.launch{
//            try {
//                val result = MiwokApi.retrofitService.showList()
//
//                if (result.isNotEmpty()) {
//                    _data.value = result
//                } else {
//                    _response.value = "Data miwok kosong!"
//                }
//            } catch (t: Throwable){
//                _response.value = "Tidak ada koneksi internet!"
//            } finally {
//                _loading.value = false
//            }
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MiwokViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MiwokViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}