package com.wayne.codetask17media.view.main

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.wayne.codetask17media.R
import com.wayne.codetask17media.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun initView() {
        progress.setOnClickListener {  }
    }

    override fun addObserver() {
        Timber.e("addObserver()")
        val viewModel: MainViewModel by viewModels()
        viewModel.isLoading.observe(this , Observer {
            Timber.e("isLoading : $it")
            when (it) {
                true -> progress.visibility = View.VISIBLE
                false -> progress.visibility = View.GONE
            }
        })
    }
}