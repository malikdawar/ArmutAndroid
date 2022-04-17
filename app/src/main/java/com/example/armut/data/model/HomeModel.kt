package com.example.armut.data.model

import com.google.gson.annotations.SerializedName

data class HomeModel(
    @SerializedName("all_services")
    val allServices: List<Service>?,
    @SerializedName("popular")
    val popular: List<Service>?,
    @SerializedName("posts")
    val posts: List<Post>?
)

data class Service(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("long_name")
    val longName: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pro_count")
    val proCount: Int?,
    @SerializedName("service_id")
    val serviceId: Int?,
    @SerializedName("average_rating")
    val averageRating: Float?,
    @SerializedName("completed_jobs_on_last_month")
    val completedJobsLastMonth: Int?
)

data class Post(
    @SerializedName("category")
    val category: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?
)