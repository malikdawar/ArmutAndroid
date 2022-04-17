package com.example.armut.ui.service

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.armut.base.BaseFragment
import com.example.armut.core.extensions.showToastMsg
import com.example.armut.core.utils.load
import com.example.armut.databinding.FragmentServiceBinding
import com.example.armut.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The ServiceFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@AndroidEntryPoint
class ServiceFragment : BaseFragment() {

    private val viewModel: ServiceViewModel by viewModels()
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentServiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initObservations()
    }

    @SuppressLint("SetTextI18n")
    private fun initObservations() {
        sharedViewModel.serviceId.observe(viewLifecycleOwner) {
            viewModel.fetchServiceById(it)
        }

        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    progressDialog.show()
                }

                is ContentState -> {
                    progressDialog.dismiss()
                }

                is ErrorState -> {
                    progressDialog.dismiss()
                    showToastMsg(state.message)
                }
            }
        }

        viewModel.servicesLiveData.observe(viewLifecycleOwner) {
            binding.imgServicePhoto.load(it.imageUrl)
            binding.tvServiceTitle.text = it.longName
            binding.tvServiceProsCount.text = it.proCount.toString()
            binding.tvServiceRating.text = it.averageRating.toString()
            binding.tvServiceLastMonth.text =
                "Last month ${it.completedJobsLastMonth} cleaning jobs completed"
        }
    }
}
