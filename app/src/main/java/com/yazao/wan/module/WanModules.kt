package com.yazao.wan.module

import com.yazao.wan.net.RetrofitManager
import com.yazao.wan.ui.app.MainActivity
import com.yazao.wan.dialog.LoadingDialog
import com.yazao.wan.ui.app.AppViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

/**
 * Description :
 * Author : yueliangrensheng
 * Date : 2020/8/30
 */
val dataSourceModule = module {

    single {
        RetrofitManager.apiService
    }

    single {
        Calendar.getInstance()
    }
}

val viewModelModule = module {
    viewModel { AppViewModel() }
}

val dialogModule = module {
    scope<MainActivity>{
        scoped { LoadingDialog(androidContext()) }
    }
}