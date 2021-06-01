package com.soham.todo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.soham.rcv_prac.myAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "todo.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    val ls = arrayListOf<Todo>()
    private val ad = myAdapter(ls)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.materialToolbar))

        floatingActionButton.setOnClickListener {
            val i = Intent(this, editTodo::class.java)
            startActivity(i)
        }



        db.DAO1().getTask().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                ls.clear()
                ls.addAll(it)
                ad.notifyDataSetChanged()
            } else {
                ls.clear()
                ad.notifyDataSetChanged()
            }
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ad
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.historyOption -> {
                startActivity(Intent(this, HistoryActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}