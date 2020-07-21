package com.wayne.codetask17media.view.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.wayne.codetask17media.R


abstract class BaseFragment(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
    }

    open fun initView() {}

    open fun addObserver() {}

    fun showErrorMessage(message: String?, okClickListener: DialogInterface.OnClickListener? = null): Dialog {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(R.string.oops)
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setPositiveButton(android.R.string.ok, okClickListener)
        return dialog.show()
    }
}