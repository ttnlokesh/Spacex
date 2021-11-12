package com.example.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex.data.network.response.LaunchResponseItem
import com.example.spacex.data.network.response.RocketResponse
import com.example.spacex.data.repository.SpaceXRepository
import com.example.spacex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SpaceXViewModel(private val spaceXRepository: SpaceXRepository): ViewModel() {

    //Livedata for launch data
    private val _errorResponseLiveData = MutableLiveData<String>()
    val errorResponseLiveData: LiveData<String> = _errorResponseLiveData


    //Livedata for rocket data
    private val _rocketResponseLiveData = MutableLiveData <Resource<Response<RocketResponse>>>()
    val rocketResponseLiveData: LiveData<Resource<Response<RocketResponse>>> = _rocketResponseLiveData

    //Livedata for progressbar
     val isProgressBarVisibleLiveData = MutableLiveData<Boolean>()

    private val _launchListLiveData = MutableLiveData<ArrayList<LaunchResponseItem>>()
    val launchListLiveData: LiveData<ArrayList<LaunchResponseItem>> = _launchListLiveData

    init {
        isProgressBarVisibleLiveData.value = false
    }

    fun getLaunchData() {
        viewModelScope.launch {
            callApiLaunchData()
        }
    }

    private suspend fun callApiLaunchData() {
        isProgressBarVisibleLiveData.value = true
        withContext(Dispatchers.IO) {
            val launchResponseAsync = async {  spaceXRepository.getLaunchList() }
            val launchResponse =  launchResponseAsync.await()
            if(launchResponse.isSuccessful) {
                _launchListLiveData.postValue(launchResponse.body())
            } else {
                _errorResponseLiveData.postValue("Error")
            }
        }
        isProgressBarVisibleLiveData.value = false
    }

    fun getRocketInfo(rocketId:String) {
        viewModelScope.launch {
            callApiRocketInfo(rocketId)
        }
    }

    private suspend fun callApiRocketInfo(rocketId:String) {
        _rocketResponseLiveData.value = Resource.loading(data = null)
        isProgressBarVisibleLiveData.value = true
        withContext(Dispatchers.IO) {
            val rocketResponseAsync = async {  spaceXRepository.getRocketInfo(rocketId) }
            val rocketResponse =  rocketResponseAsync.await()
            if(rocketResponse.isSuccessful) {
                _rocketResponseLiveData.postValue(Resource.success(data = rocketResponse))
            }else {
                _errorResponseLiveData.postValue("Error")
            }
        }
        isProgressBarVisibleLiveData.value = false
    }
}