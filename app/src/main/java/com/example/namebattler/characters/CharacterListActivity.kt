package com.example.namebattler.characters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.namebattler.CharaListAdapter
import com.example.namebattler.R

class CharacterListActivity : AppCompatActivity() {
    // RecyclerView 本体、および、LayoutManager と Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>

    // Adapter にセットするデータ (1～100)
    private val data = IntArray(100) { it + 1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        layoutManager = LinearLayoutManager(this)
        adapter = CharaListAdapter(data)
        recyclerView = findViewById<RecyclerView>(R.id.list_view).also {
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }
}
