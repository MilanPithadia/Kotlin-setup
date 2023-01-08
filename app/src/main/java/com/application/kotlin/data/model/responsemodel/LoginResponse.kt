package com.application.kotlin.data.model.responsemodel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.application.kotlin.data.enum.UserType

@Keep
data class LoginResponse(

    @SerializedName("UserId")
    val userId: String = "0",

    @SerializedName("Message")
    val message: String = "",

    @SerializedName("Status")
    val status: Boolean = false,

    @SerializedName("Token")
    val token: String = "",

    @SerializedName("UserType")
    val type : String  = "",

)