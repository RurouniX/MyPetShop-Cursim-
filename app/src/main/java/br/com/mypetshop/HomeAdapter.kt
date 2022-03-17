package br.com.mypetshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class HomeAdapter(private val itemList: List<ItemListModel>, private val callback : ()-> Unit) :
    RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.adapter_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(itemList, position, callback)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.home_adapter_title)
    private val subtitle = itemView.findViewById<TextView>(R.id.home_adapter_subtitle)
    private val check = itemView.findViewById<CheckBox>(R.id.home_adapter_check)

    fun bind(itemList: List<ItemListModel>, position: Int, callback : ()-> Unit) {

        val item = itemList[position]

        title.text = item.title
        subtitle.text = item.subtitle

        with(check) {
            isChecked = item.isActive

            setOnClickListener {

                itemList.forEach {
                    it.isActive = false
                }

                item.isActive = true

                callback()

            }
        }

        with(itemView) {
            setOnClickListener {


                check.isChecked = !check.isChecked


                Toast.makeText(
                    itemView.context,
                    "Título - ${item.title} --- Subtitulo - ${item.subtitle} --- IsChecked - ${item.isActive}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (position % 2 == 0)
                setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.yellow))
            else
                setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue))

        }
    }
}