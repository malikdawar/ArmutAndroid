package com.example.armut.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.armut.core.utils.load
import com.example.armut.data.model.Post
import com.example.armut.databinding.RowItemLatestBlogServicesBinding

class BlogPostsAdapter :
    RecyclerView.Adapter<BlogPostsAdapter.BlogPostViewHolder>() {

    lateinit var onPostItemSelected: (Post) -> Unit
    private val postItems: ArrayList<Post> = arrayListOf()

    fun setItems(posts: List<Post>) {
        postItems.clear()
        postItems.addAll(posts)
        notifyItemRangeInserted(0, postItems.size)
    }

    fun onPostItemSelectionListener(listener: (Post) -> Unit) {
        onPostItemSelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogPostsAdapter.BlogPostViewHolder {
        return BlogPostViewHolder(
            RowItemLatestBlogServicesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BlogPostViewHolder, position: Int) {
        holder.bind(postItems[position])
    }

    override fun getItemCount() = postItems.size

    inner class BlogPostViewHolder(private val rowItemLatestBlogServicesBinding: RowItemLatestBlogServicesBinding) :
        RecyclerView.ViewHolder(rowItemLatestBlogServicesBinding.root) {

        fun bind(post: Post) {
            rowItemLatestBlogServicesBinding.apply {
                itemImgViewPostPoster.load(post.imageUrl)
                itemTextPostCategory.text = post.category
                itemTextServicesDetails.text = post.title
            }
        }
    }
}
