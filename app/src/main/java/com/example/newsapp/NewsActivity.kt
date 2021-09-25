package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity(), RVClickListener {

    private lateinit var mBinding: ActivityNewsBinding
    private lateinit var mAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        loadData()
        mBinding.recyclerViewNews.layoutManager = LinearLayoutManager(this)
        mAdapter = NewsAdapter(this)
        mBinding.recyclerViewNews.adapter = mAdapter
    }

    private fun loadData() {

        val url = "https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val list = ArrayList<Model>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = Model (
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("publishedAt"),
                        newsJsonObject.getString("url")
                    )
                    list.add(news)
                }
                Log.i("value", list.size.toString())
                mAdapter.updateList(list)
            },
            { Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show() })

        APICall.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClickListener(model: Model) {
        val intent = Intent(applicationContext, ArticleWebview::class.java)
        intent.putExtra("urls", model.url)
        startActivity(intent)
    }
}