package org.d3if4055.miwokapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.network.MiwokApi

@Suppress("SpellCheckingInspection")
class MiwokViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading

    private val _data = MutableLiveData<List<Miwok>>()
    val data : LiveData<List<Miwok>>
        get() = _data

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    init {
        _response.value = ""
        initData()
    }

    private fun initData() {
        _loading.value = true

        uiScope.launch{
            try {
                val result = MiwokApi.retrofitService.showList()

                if (result.isNotEmpty()) {
                    _data.value = result
                } else {
                    _response.value = "Data miwok kosong!"
                }
            } catch (t: Throwable){
                _response.value = "Tidak ada koneksi internet!"
            } finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}