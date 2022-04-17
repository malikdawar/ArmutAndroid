package com.example.armut.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.armut.R
import com.example.armut.core.utils.load
import com.example.armut.data.model.Service
import com.example.armut.databinding.RowItemAllServicesBinding
import com.example.armut.databinding.RowItemPopularServicesBinding

class ServicesAdapter(private val adapterViewType: AdapterViewType) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class AdapterViewType {
        ALL,
        POPULAR
    }

    private lateinit var onServiceItemSelected: (Service) -> Unit
    private val serviceItems: ArrayList<Service> = arrayListOf()

    fun setItems(services: List<Service>) {
        serviceItems.clear()
        serviceItems.addAll(services)
        notifyItemRangeInserted(0, serviceItems.size)
    }

    fun onServicesItemSelectionListener(listener: (Service) -> Unit) {
        onServiceItemSelected = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (adapterViewType) {
            AdapterViewType.ALL -> {
                return AllServicesViewHolder(
                    RowItemAllServicesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return PopularServicesViewHolder(
                    RowItemPopularServicesBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val service: Service = serviceItems[position]
        when (adapterViewType) {
            AdapterViewType.ALL -> {
                (holder as AllServicesViewHolder).bind(service)
            }
            AdapterViewType.POPULAR -> {
                (holder as PopularServicesViewHolder).bind(service)
            }
        }
    }

    override fun getItemCount() = serviceItems.size

    inner class AllServicesViewHolder(private val rowItemAllServicesBinding: RowItemAllServicesBinding) :
        RecyclerView.ViewHolder(rowItemAllServicesBinding.root) {

        fun bind(service: Service) {
            rowItemAllServicesBinding.apply {
                if (service.serviceId == -1) {
                    itemImgViewServiceIcon.setImageResource(R.drawable.ic_diger)
                    itemImgViewServiceIcon.scaleType = ImageView.ScaleType.CENTER
                } else {
                    itemImgViewServiceIcon.load(service.imageUrl)
                }
                itemTextServiceTitle.text = service.name
            }
        }
    }

    inner class PopularServicesViewHolder(private val rowItemPopularServicesBinding: RowItemPopularServicesBinding) :
        RecyclerView.ViewHolder(rowItemPopularServicesBinding.root) {

        fun bind(service: Service) {
            rowItemPopularServicesBinding.apply {
                itemImgViewServicesIcon.load(service.imageUrl)
                itemTextServicesTitle.text = service.name
            }
        }
    }
}
