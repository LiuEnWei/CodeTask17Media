package com.wayne.codetask17media.view.base

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.wayne.codetask17media.R
import timber.log.Timber

abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorBlack)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val uiVisibility = window.decorView.systemUiVisibility
                window.decorView.systemUiVisibility = uiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        Timber.e("onCreate()")

        initView()
        addObserver()
    }


    open fun initView() {}

    open fun addObserver() {}
}