package com.greemoid.githubmessanger.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.core.Abstract
import com.greemoid.githubmessanger.presentation.core.CustomImageView
import com.greemoid.githubmessanger.presentation.core.CustomTextView

class SearchUserAdapter : RecyclerView.Adapter<SearchUserViewHolder>(),
    Abstract.Mapper.Data<List<SearchUserUi>, Unit> {

    private val list = ArrayList<SearchUserUi>()

    override fun getItemViewType(position: Int): Int = when (list[position]) {
        is SearchUserUi.Base -> 0
        is SearchUserUi.Empty -> 1
        is SearchUserUi.NoResults -> 2
        is SearchUserUi.Search -> 3
        else -> 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder =
        when (viewType) {
            0 -> SearchUserViewHolder.Base(R.layout.search_user_result.view(parent))
            1 -> SearchUserViewHolder.Progress(R.layout.progress.view(parent))
            2 -> SearchUserViewHolder.NoResults(R.layout.no_results.view(parent))
            3 -> SearchUserViewHolder.Initial(R.layout.search.view(parent))
            else -> throw IllegalStateException("unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    override fun map(data: List<SearchUserUi>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}

private fun Int.view(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)

abstract class SearchUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: SearchUserUi) = Unit

    class Base(view: View) : SearchUserViewHolder(view) {

        private val avatar = itemView.findViewById<CustomImageView>(R.id.avatarImageView)
        private val userName = itemView.findViewById<CustomTextView>(R.id.userNameTextView)
        private val userLogin = itemView.findViewById<CustomTextView>(R.id.userLoginTextView)
        override fun bind(item: SearchUserUi) {
            item.map(avatar, userLogin, userName)
        }
    }


    class Initial(view: View) : SearchUserViewHolder(view)
    class Progress(view: View) : SearchUserViewHolder(view)
    class NoResults(view: View) : SearchUserViewHolder(view)
}
