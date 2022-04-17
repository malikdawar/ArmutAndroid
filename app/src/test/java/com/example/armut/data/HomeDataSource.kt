package com.example.armut.data

import com.example.armut.data.model.HomeModel
import com.example.armut.data.model.Post
import com.example.armut.data.model.Service

object HomeDataSource {

    fun getHomeComponents(): HomeModel {
        return HomeModel(
            allServices = arrayListOf(serviceItem),
            popular = arrayListOf(serviceItem),
            posts = arrayListOf(postItem)
        )
    }

    private val serviceItem: Service
        get() = Service(
            id = 1,
            serviceId = 1,
            imageUrl = "http://dummyimage.com/500x500.png/5fa2dd/ffffff",
            proCount = 206,
            name = "Tamiz",
            longName = "Tamiz ipsumTitle",
            averageRating = null,
            completedJobsLastMonth = null
        )

    private val postItem: Post
        get() = Post(
            title = "Tamiz",
            category = "Tamiz",
            imageUrl = "http://dummyimage.com/500x500.png/5fa2dd/ffffff",
            link = "http://dummyimage.com/500x500.png/5fa2dd/ffffff"
        )
}