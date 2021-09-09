package com.didi.githubuser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailUser(
    val  login: String?,
    val  name: String?,
    val  avatar_url: String?,
    val  bio: String?,
    val  follower: Int,
    val  following: Int,
    val  repository: Int,
    val  location: String?
): Parcelable