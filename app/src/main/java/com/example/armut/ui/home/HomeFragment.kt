package com.example.armut.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.armut.base.BaseFragment
import com.example.armut.core.extensions.conditionallyVisible
import com.example.armut.core.extensions.replaceFragment
import com.example.armut.core.extensions.showToastMsg
import com.example.armut.databinding.FragmentHomeBinding
import com.example.armut.ui.MainViewModel
import com.example.armut.ui.home.adapters.BlogPostsAdapter
import com.example.armut.ui.home.adapters.ServicesAdapter
import com.example.armut.ui.service.ServiceFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding
    private var servicesAdapter: ServicesAdapter =
        ServicesAdapter(ServicesAdapter.AdapterViewType.ALL)
    private var popularServicesAdapter: ServicesAdapter =
        ServicesAdapter(ServicesAdapter.AdapterViewType.POPULAR)
    private var blogPostAdapter: BlogPostsAdapter = BlogPostsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerViews()
        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    controlViewVisibility(false)
                    progressDialog.show()
                }

                is ContentState -> {
                    controlViewVisibility(true)
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.allServicesLiveData.observe(viewLifecycleOwner) {
            servicesAdapter.setItems(it)
        }

        viewModel.popularServicesLiveData.observe(viewLifecycleOwner) {
            popularServicesAdapter.setItems(it)
        }

        viewModel.blogPostsLiveData.observe(viewLifecycleOwner) {
            blogPostAdapter.setItems(it)
        }
    }

    private fun initRecyclerViews() {
        servicesAdapter.apply {
            onServicesItemSelectionListener { service ->
                service.serviceId?.let {
                    sharedViewModel.setServiceId(serviceId = it)
                    replaceFragment(ServiceFragment())
                }
            }
            stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            binding.recyclerAllServices.adapter = this
        }

        popularServicesAdapter.apply {
            onServicesItemSelectionListener { service ->
                service.serviceId?.let {
                    sharedViewModel.setServiceId(serviceId = it)
                    replaceFragment(ServiceFragment())
                }
            }
            binding.rvPopularServices.adapter = this
        }

        binding.rvBlogPosts.adapter = blogPostAdapter
    }

    private fun controlViewVisibility(shouldVisible: Boolean) {
        binding.recyclerAllServices.conditionallyVisible(shouldVisible)
        binding.tvLabelAllServices.conditionallyVisible(shouldVisible)

        binding.tvLabelPopularServices.conditionallyVisible(shouldVisible)
        binding.rvPopularServices.conditionallyVisible(shouldVisible)

        binding.tvBlogPostsTitle.conditionallyVisible(shouldVisible)
        binding.rvBlogPosts.conditionallyVisible(shouldVisible)
    }
}
