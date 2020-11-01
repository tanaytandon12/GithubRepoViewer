package com.tanay.githubrepoviewer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.tanay.githubrepoviewer.R
import com.tanay.githubrepoviewer.appComponent
import com.tanay.githubrepoviewer.ui.di.GithubRepoModule

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RepoListFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
        }
    }
}