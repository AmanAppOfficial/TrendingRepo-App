package com.example.gitdemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitdemoapp.network.ApiInterface
import com.example.gitdemoapp.network.RetrofitHelper
import com.example.model.RepoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * view model to hold list of repo's
 **/
@HiltViewModel
class MainViewModel @Inject constructor(var apiInterface: ApiInterface): ViewModel() {

    val repoList = MutableLiveData<List<RepoModel>>()

    init {
        getData()
    }

    // get original data from api in IO thread.
    // Kotlin coroutines
    private fun getData(){
        viewModelScope.launch(IO) {
            val homeDataResult = apiInterface.getHomeData().body()
            withContext(Dispatchers.Main){
                repoList.value = homeDataResult
            }
        }
    }
}