package com.tanay.githubrepoviewer.ui.repo_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tanay.githubrepoviewer.R
import com.tanay.githubrepoviewer.appComponent
import com.tanay.githubrepoviewer.ui.di.GithubRepoModule
import com.tanay.githubrepoviewer.ui.repo_list.IRepoListViewModel
import javax.inject.Inject

class RepoListFragment : Fragment() {

    @Inject
    lateinit var mViewModel: IRepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.repoListComponent(GithubRepoModule(requireActivity()))
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

}