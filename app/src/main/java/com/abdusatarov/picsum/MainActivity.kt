package com.abdusatarov.picsum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdusatarov.picsum.api.ImageListItem
import com.abdusatarov.picsum.api.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.main_recycler)
        recycler.layoutManager = GridLayoutManager(this, 2)

        NetworkHelper.getService()
            .getImageList()
            .enqueue(object : Callback<List<ImageListItem>> {
                override fun onFailure(call: Call<List<ImageListItem>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<ImageListItem>>,
                    response: Response<List<ImageListItem>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        adapter = MainAdapter(response.body()!!){item ->
                            val intent = Intent(this@MainActivity, DetailActivity::class.java)
                            intent.putExtra("id", item.id)
                            startActivity(intent)

                        }
                        recycler.adapter = adapter
                    }
                }

            })
    }
}