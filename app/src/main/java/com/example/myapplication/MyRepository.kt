package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MyRepository{

    companion object{
        private val STATUS_TYPE_Object:Type = object: TypeToken<ArrayList<MainModel>>(){}.type
    }
    fun getServerData():Single<ArrayList<MainModel>>{
        return Single.create({source->
            var responseCall:Call<ResponseBody> = MainHttpClient.getRestClient(MainConstants.BASE_URL+"comments/").
            create(MainApiResponse::class.java).comments
            responseCall.enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    source.onError(Throwable("There is an error"))
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    var responseString:String = response.body().toString().replace("\"[","[").replace("]\"","]");
                    source.onSuccess(Gson().fromJson(responseString,STATUS_TYPE_Object) as ArrayList<MainModel> )
                }
            })
        })
    }
}