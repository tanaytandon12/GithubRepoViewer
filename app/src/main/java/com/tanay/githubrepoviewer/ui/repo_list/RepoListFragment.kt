package com.tanay.githubrepoviewer.ui.repo_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tanay.githubrepoviewer.R
import com.tanay.githubrepoviewer.appComponent
import com.tanay.githubrepoviewer.data.models.RepoListState
import com.tanay.githubrepoviewer.ui.di.GithubRepoModule
import com.tanay.githubrepoviewer.ui.repo_list.IRepoListViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject

class RepoListFragment : Fragment() {

    @Inject
    lateinit var mViewModel: IRepoListViewModel

    private lateinit var mAdapter: RepoListAdapter

    private var mSnackBar: Snackbar? = null

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
        setupList()
        observeListData()
        observeFetchingData()
    }

    private fun setupList() {
        mAdapter = RepoListAdapter(requireContext())
        rvRepoList.adapter = mAdapter
        rvRepoList.setHasFixedSize(true)
        rvRepoList.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    private fun observeListData() {
        mViewModel.repoList().observe(viewLifecycleOwner, Observer {
            setNoDataMessageVisibility(it.isEmpty())
            mAdapter.submitList(it)
        })
    }

    private fun observeFetchingData() {
        mViewModel.repoListState().observe(viewLifecycleOwner, Observer {
            when (it) {
                RepoListState.Loading -> {
                    showLoadingView()
                }
                RepoListState.Fetched -> {
                    onDataFetched()
                }
                is RepoListState.Failure -> {
                    onError(it.errorMessage)
                }
                RepoListState.LimitReached -> {
                    onLimitReached()
                }
                else -> {
                    // doing nothing
                }
            }
        })
    }

    private fun showLoadingView() {
        mSnackBar?.dismiss()
        mSnackBar = Snackbar.make(parent, R.string.fetchingData, Snackbar.LENGTH_INDEFINITE)
        mSnackBar?.show()
        progressBar.visibility = View.VISIBLE
    }

    private fun onDataFetched() {
        mSnackBar?.dismiss()
        progressBar.visibility = View.GONE
    }

    private fun onLimitReached() {
        progressBar.visibility = View.GONE
        mSnackBar?.dismiss()
        mSnackBar = Snackbar.make(parent, R.string.allDataFetched, Snackbar.LENGTH_SHORT)
        mSnackBar?.show()
    }

    private fun onError(errorMessage: String?) {
        mSnackBar?.dismiss()
        mSnackBar = Snackbar.make(
            parent,
            errorMessage ?: getString(R.string.networkError),
            Snackbar.LENGTH_SHORT
        )
        mSnackBar?.show()
        progressBar.visibility = View.GONE
    }

    private fun setNoDataMessageVisibility(isVisible: Boolean) {
        tvMessage.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}