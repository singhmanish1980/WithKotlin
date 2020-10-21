package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewmodel:MyViewModel
    lateinit var adapter: MyRecyclerAdapter
    lateinit var layoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        viewmodel = ViewModelProviders.of(this,MyViewModel.MyViewModelFactory(Integer(10))).run {get(MyViewModel::class.java)
        }
        viewmodel.myLiveData.observe(this, Observer {list:ArrayList<MainModel>-> apply {
            if(adapter == null) {
                adapter = MyRecyclerAdapter(list)
                adapter?.also {
                    this.layoutManager = LinearLayoutManager(this)
                    binding.recyclerView.adapter = it
                    binding.recyclerView.layoutManager = layoutManager
                }
            }else{
                adapter.addDataToList(list)
            }}})
        viewmodel.getDataFromServer()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}