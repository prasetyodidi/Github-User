package com.didi.githubuser.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailUser(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("url")
	val url: String,
)
