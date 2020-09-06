package com.yazao.wan.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.yazao.wan.App
import com.yazao.wan.entity.BannerData
import com.yazao.wan.scope.safeLaunch
import com.yazao.wan.util.PreferenceHelper

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    var banners = MutableLiveData<List<BannerData>>()

    init {
        banners.value = repository.getCachedBanners()
    }

    fun getBanners() {
        viewModelScope.safeLaunch {
            block = {
                banners.value = repository.getBanners().apply {
                    PreferenceHelper.putBannerCache(App.instance, bannerJson = Gson().toJson(this))
                }
            }
        }
    }

}
