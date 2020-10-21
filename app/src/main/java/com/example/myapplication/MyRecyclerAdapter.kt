package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(var myDataList:ArrayList<MainModel>):RecyclerView.Adapter<MyRecyclerAdapter.MyRecyclerVH>(){
    class MyRecyclerVH(view:View):RecyclerView.ViewHolder(view){
            val view1:TextView = view.findViewById(R.id.id1)
            val view2:TextView = view.findViewById(R.id.id2)
            val view3:TextView = view.findViewById(R.id.id3)
            val view4:TextView = view.findViewById(R.id.id4)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: MyRecyclerVH, position: Int) {
        holder.view1.text = myDataList.get(position).name
        holder.view2.text = myDataList.get(position).body
        holder.view3.text = myDataList.get(position).email
        holder.view4.text = ""+myDataList.get(position).id
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerVH =
       MyRecyclerVH(
            LayoutInflater.from(
                parent.getContext()
            ).inflate(R.layout.list_item, parent, false)
        )

    fun addDataToList(mylist:ArrayList<MainModel>){
        myDataList.addAll(mylist)
        notifyDataSetChanged()
    }
}