package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import kotlin.collections.ArrayList

class MyViewModel(val pageNo:Integer):ViewModel(){
    val myLiveData:MutableLiveData<ArrayList<MainModel>> = MutableLiveData()

    @Inject
    lateinit var myRepository:MyRepository

    init {
        MyInjector.INSTANCE.getMyComponent().inject(this)
    }

    fun getDataFromServer():Unit{
        myRepository.getServerData().observeOn(AndroidSchedulers.mainThread()).subscribe(object:SingleObserver<ArrayList<MainModel>>{
            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(t: ArrayList<MainModel>) {
                myLiveData.value = t
            }
        })
    }
    class MyViewModelFactory(var pageNo:Integer):ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MyViewModel(pageNo) as T
        }
    }
}


