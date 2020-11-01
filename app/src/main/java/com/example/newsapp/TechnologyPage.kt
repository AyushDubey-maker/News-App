package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.collections.ArrayList

class TechnologyPage : AppCompatActivity(), NewsItemClicked {
    val list=ArrayList<String>()
    val displayList=ArrayList<String>()
    private lateinit var madapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val recyclerView=findViewById(R.id.recycleView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchData()
        madapter= RecyclerAdapter(this)
        recyclerView.adapter = madapter
    }
    private fun fetchData() {
        val url= "https://newsapi.org/v2/everything?domains=techcrunch.com,thenextweb.com&apiKey=264dbc5a48d04391b550142f1f1a6bf1"
        val jsonObject= JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                {
                    val newsJsonArray=it.getJSONArray("articles")
                    val newsArray=ArrayList<News>()
                    for(i in 0 until newsJsonArray.length()) {

                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news=News(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")




                        )
                        newsArray.add(news)

                    }
                    madapter.updateNews(newsArray)
                },
                {

                }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObject)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        val searchItem=menu.findItem(R.id.search_icon)
        if(searchItem!=null){
            val searchView=searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search=newText.toLowerCase(Locale.getDefault())
                        list.forEach{

                        }

                    } else{

                    }
                    return true
                }

            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@TechnologyPage, MainActivity::class.java))
                finish()
                return true
            }
            R.id.search_icon ->{
            }
        }
        return false
    }

    override fun onItemClicked(item: News) {
        val builder= CustomTabsIntent.Builder()
        val customTabsIntent=builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}