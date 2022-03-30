package br.com.mypetshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.home_list) }
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val itemToPopulateAdapter = populateList()

         adapter = HomeAdapter(itemToPopulateAdapter){

            adapter.notifyDataSetChanged()

        }

        listView.adapter = adapter

    }

    private fun populateList(): List<ItemListModel> {

        val itemsList = mutableListOf<ItemListModel>()

        for (i in 1..100) {

            val item = ItemListModel("Titulo $i", "Subtitulo $i")
            itemsList.add(item)

        }

        return itemsList.toList()

    }

}

data class ItemListModel(val title: String, val subtitle: String, var isActive: Boolean = false)
