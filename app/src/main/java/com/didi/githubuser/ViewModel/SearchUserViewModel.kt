package com.didi.githubuser.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.didi.githubuser.model.ItemsItem
import com.didi.githubuser.model.ResponseSearchUser
import com.didi.githubuser.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel: ViewModel() {
    companion object {
        private val TAG = SearchUserViewModel::class.java.simpleName
    }

    private val _listUsers = MutableLiveData<List<ItemsItem>>()
    val listUsers: LiveData<List<ItemsItem>> = _listUsers
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchUsers(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(username)
        client.enqueue(object : Callback<ResponseSearchUser>{
            override fun onResponse(
                call: Call<ResponseSearchUser>,
                response: Response<ResponseSearchUser>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (responseBody != null){
                    _listUsers.value = responseBody.items
                }else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

//    fun setSearchUser(username: String) {
//        val listItems = ArrayList<ListUser>()
//        val client = AsyncHttpClient()
//        val url = "https://api.github.com/search/users?q=$username"
//
//        client.addHeader("Authorization", GITHUB_API_KEY)
//        client.addHeader("User-Agent", "request")
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray
//            ) {
//                try {
//                    val result = String(responseBody)
//                    val responseObject = JSONObject(result)
//                    val list = responseObject.getJSONArray("items")
//
//                    Log.d(TAG + " result", result)
//
//                    if (list.length() != 0){
//                        for (i in 0 until list.length()) {
//                            val user = list.getJSONObject(i)
//                            val login = user.getString("login")
//                            val avatr_url = user.getString("avatar_url")
//                            val url_detail = user.getString("url")
//                            val html_url = user.getString("html_url")
//                            val userItems = ListUser(login, avatr_url, url_detail, html_url)
//                            listItems.add(userItems)
//                        }
//                    }
//
//                    listUsers.postValue(listItems)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Log.d(TAG + " error", e.message.toString())
//                }
//            }
//
//            override fun onFailure(
//                statusCode: Int,
//                headers: Array<out Header>?,
//                responseBody: ByteArray?,
//                error: Throwable?
//            ) {
//                Log.d(TAG + " failure", error?.message.toString())
//                listUsers.postValue(listItems)
//            }
//
//        })
//    }

    fun getSearchUser(): LiveData<List<ItemsItem>> {
        return listUsers
    }


}
