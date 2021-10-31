package com.kaushik.demostandardapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaushik.demostandardapp.databinding.EachRowBinding
import com.kaushik.demostandardapp.network.models.Post

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding: EachRowBinding
    private lateinit var postList: Post

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.eachRow.text = postList[position].body
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setData(postList: Post) {
        this.postList = postList
        notifyDataSetChanged()
    }

}