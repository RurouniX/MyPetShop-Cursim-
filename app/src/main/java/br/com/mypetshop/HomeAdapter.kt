package br.com.mypetshop

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val itemList: List<String>) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: String) {

        Log.d("ItemAdapter", item)
        itemView.findViewById<TextView>(R.id.adapter_text).text = item

    }
}