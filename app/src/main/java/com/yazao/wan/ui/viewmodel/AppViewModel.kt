package com.yazao.wan.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    val showLoadingProgress = MutableLiveData<Boolean>()

    val reloadHomeData = MutableLiveData<Boolean>()

    val reloadCollectWebsite = MutableLiveData<Boolean>()

    val needUpdateTodoList = MutableLiveData<Boolean>()

    fun showLoading() = showLoadingProgress.postValue(true)
    fun hideLoading() = showLoadingProgress.postValue(false)

}
