package com.didi.githubuser.adapter

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.didi.githubuser.R
import com.didi.githubuser.databinding.ListUserBinding
import com.didi.githubuser.model.ResponseItem

class ListUsersAdapter: RecyclerView.Adapter<ListUsersAdapter.ListUsersViewHolder>() {
    private val mData = ArrayList<ResponseItem>()
    private var onItemClickCallback: OnItemClickCallback? = null
    private var onBtnGithubClickCallback: OnBtnGithubClickCallback? = null

    fun setOnBtnGithubClickCallback(onBtnGithubClickCallback: OnBtnGithubClickCallback){
        this.onBtnGithubClickCallback = onBtnGithubClickCallback
    }

    interface OnBtnGithubClickCallback {
        fun onItemClicked(data: ResponseItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemCLicked(data: ResponseItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(item: ArrayList<ResponseItem>){
        mData.clear()
        mData.addAll(item)
        notifyDataSetChanged()
    }

    inner class ListUsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ListUserBinding.bind(itemView)
        fun bind(listUser: ResponseItem){
            val url = listUser.avatarUrl
            val linkGithub = SpannableString(listUser.htmlUrl)
            linkGithub.setSpan(UnderlineSpan(), 0, linkGithub.length, 0)

            binding.image.loadImage(url)
            binding.tvUsername.text = listUser.login
            binding.tvUrlHtml.text = linkGithub
            itemView.setOnClickListener{
                onItemClickCallback?.onItemCLicked(listUser)
            }

            binding.tvUrlHtml.setOnClickListener {
                onBtnGithubClickCallback?.onItemClicked(listUser)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListUsersViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return ListUsersViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ListUsersAdapter.ListUsersViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    fun ImageView.loadImage(url: String){
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().override(100, 100))
            .centerCrop()
            .into(this)
    }
}