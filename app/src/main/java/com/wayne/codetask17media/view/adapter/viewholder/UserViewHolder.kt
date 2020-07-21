package com.wayne.codetask17media.view.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wayne.codetask17media.R

class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val topLine: View = itemView.findViewById(R.id.line_top)
    val imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    val textLogin: TextView = itemView.findViewById(R.id.text_login)
    val textHtmlUrl: TextView = itemView.findViewById(R.id.text_html_url)
}