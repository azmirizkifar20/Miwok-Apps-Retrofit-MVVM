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
import org.d3if4055.miwokapps.model.NO_CONNECTION
import org.d3if4055.miwokapps.database.MiwokDatabase
import org.d3if4055.miwokapps.repository.MiwokRepository

@Suppress("SpellCheckingInspection")
class MiwokViewModel(application: Application) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean>
        get() = _loading

    private val _response = MutableLiveData<String>()
    val response : LiveData<String>
        get() = _response

    private var job = Job()
    private val uiScope = CoroutineScope(job + Dispatchers.Main)

    private val database = MiwokDatabase.getInstance(application)
    private val miwokRepository = MiwokRepository(database)

    init {
        _loading.value = true
        uiScope.launch {
            try {
                miwokRepository.refreshMiwok()
            } catch (t: Throwable){
                _response.value = NO_CONNECTION
            } finally {
                _loading.value = false
            }
        }
    }

    // data dari repository
    val miwok = miwokRepository.miwok

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