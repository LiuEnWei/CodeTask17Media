package com.wayne.codetask17media.view.users

import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wayne.codetask17media.R
import com.wayne.codetask17media.utils.hideSoftInput
import com.wayne.codetask17media.view.adapter.UsersAdapter
import com.wayne.codetask17media.view.adapter.paging.PagingListener
import com.wayne.codetask17media.view.base.BaseFragment
import com.wayne.codetask17media.view.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_users.*
import timber.log.Timber


class UsersFragment : BaseFragment(R.layout.fragment_users) {
    private val viewModel: UsersViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun initView() {
        recycler_users.adapter = UsersAdapter()
        edit_query.setOnEditorActionListener { textView, actionId, keyEvent ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    edit_query.hideSoftInput(requireActivity())
                    edit_query.clearFocus()
                    viewModel.getUsers(textView.text.toString(), listener)
                    false
                }
                else -> true
            }
        }
    }

    override fun addObserver() {
        viewModel.usersData.observe(viewLifecycleOwner, Observer {
            Timber.e("usersData $it")
            (recycler_users.adapter as UsersAdapter).submitList(it)
        })
    }

    private val listener = object : PagingListener {
        override fun onLoading() {
            Timber.e("onLoading")
            mainViewModel.startLoading()
        }

        override fun onTotalCount(count: Int) {
            // Nothing to do here
        }

        override fun onLoaded() {
            mainViewModel.stopLoading()
        }

        override fun onThrowable(throwable: Throwable) {
            Timber.e("throwable: $throwable")
            showErrorMessage(throwable.message)
        }
    }
}