package com.example.gitdemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitdemoapp.network.ApiInterface
import com.example.gitdemoapp.network.RetrofitHelper
import com.example.model.RepoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * view model to hold list of repo's
 **/
class MainViewModel: ViewModel() {

    val repoList = MutableLiveData<List<RepoModel>>()

    init {
        getData()
    }

    // get original data from api in IO thread.
    // Kotlin coroutines
    private fun getData(){
        viewModelScope.launch(IO) {
            val homeApi = RetrofitHelper.getInstance().create(ApiInterface::class.java)
            val homeDataResult = homeApi.getHomeData().body()
            withContext(Dispatchers.Main){
                repoList.value = homeDataResult
            }
        }
    }
}