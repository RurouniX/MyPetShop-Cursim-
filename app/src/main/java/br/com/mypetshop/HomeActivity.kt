package br.com.mypetshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {

    private val listView: RecyclerView by lazy { findViewById(R.id.home_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val adapter = HomeAdapter(listOf("Marcos", "Akanbi", "Wellington", "Gabriella"))

        listView.adapter = adapter

    }

}