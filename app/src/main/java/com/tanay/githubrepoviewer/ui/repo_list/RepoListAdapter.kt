package com.tanay.githubrepoviewer.ui.repo_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tanay.githubrepoviewer.R
import com.tanay.githubrepoviewer.data.models.local.GithubRepo
import com.tanay.githubrepoviewer.launchUrl
import kotlinx.android.synthetic.main.item_repo_list.view.*

class RepoListAdapter(context: Context) :
    PagedListAdapter<GithubRepo, RepoListAdapter.RepoListViewHolder>(
        DIFF_CALLBACK
    ) {

    private val mLayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder =
        RepoListViewHolder(mLayoutInflater.inflate(R.layout.item_repo_list, parent, false))

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName = itemView.tvName
        private val tvIssueCount = itemView.tvIssueCount
        private val tvDescription = itemView.tvDescription
        private val tvLicense = itemView.tvLicense
        private val tvAdminPermissiom = itemView.tvAdminPerm
        private val tvPullPermission = itemView.tvPullPerm
        private val tvPushPermission = itemView.tvPushPerm

        private val hasPermissionTvColor = ContextCompat.getColor(itemView.context, R.color.green)
        private val noPermissionTvColor = ContextCompat.getColor(itemView.context, R.color.red)
        private val linkTextColor = ContextCompat.getColor(itemView.context, R.color.blue)
        private val noLinkTextColor = ContextCompat.getColor(itemView.context, R.color.gray)

        private val na = itemView.context.getString(R.string.na)

        fun bind(repo: GithubRepo) {
            tvName.text = itemView.context.getString(
                R.string.repoName, if (repo.name?.isNotEmpty() == true)
                    repo.name else na
            )
            tvIssueCount.text = itemView.context.getString(
                R.string.issueCount,
                repo.openIssues ?: 0
            )
            tvDescription.text = itemView.context.getString(
                R.string.description,
                if (repo.description?.isNotEmpty() == true) repo.description else na
            )
            tvLicense.text = itemView.context.getString(R.string.licenseName, repo.licenseName)
            tvLicense.setTextColor(if (repo.licenseUrlExists()) linkTextColor else noLinkTextColor)
            tvLicense.setOnClickListener(if (repo.licenseUrlExists()) View.OnClickListener {
                launchUrl(itemView.context, repo.licenseUrl!!)
            } else null)
            tvPushPermission.setTextColor(if (repo.hasAdminPermission) hasPermissionTvColor else noPermissionTvColor)
            tvAdminPermissiom.setTextColor(if (repo.hasAdminPermission) hasPermissionTvColor else noPermissionTvColor)
            tvPullPermission.setTextColor(if (repo.hasPullPermission) hasPermissionTvColor else noPermissionTvColor)
        }
    }
}