package com.qingmei2.sample.main

import android.arch.lifecycle.LifecycleOwner
import android.databinding.ObservableField
import com.qingmei2.rhine.base.viewmodel.RhineViewModel
import com.qingmei2.rhine.ext.lifecycle.bindLifecycle

class MainViewModel : RhineViewModel() {

    val userInfo: ObservableField<String> = ObservableField("213456")

    override fun onCreate(lifecycleOwner: LifecycleOwner) {
        super.onCreate(lifecycleOwner)
        fetchUserInfo()
    }

    fun fetchUserInfo() {
        serviceManager.userInfoService
                .getUserInfo("qingmei2")
                .map { it.toString() }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .bindLifecycle(this)
                .subscribe { info ->
                    userInfo.set(info)
                }
    }
}