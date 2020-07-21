package com.wayne.codetask17media.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.wayne.codetask17media.R
import com.wayne.codetask17media.model.api.vo.User
import com.wayne.codetask17media.view.adapter.viewholder.UserViewHolder

class UsersAdapter: PagedListAdapter<User, UserViewHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let {
            if (position == 0) {
                holder.topLine.visibility = View.INVISIBLE
            } else {
                holder.topLine.visibility = View.VISIBLE
            }
            Glide.with(holder.itemView.context)
                .load(it.avatarUrl)
                .placeholder(R.drawable.bg_placeholder)
                .circleCrop()
                .into(holder.imgAvatar)
            holder.textLogin.text = it.login
            holder.textHtmlUrl.text = it.htmlUrl
        }
    }

}