package com.example.gitdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.gitdemoapp.adapter.MainAdapter
import com.example.gitdemoapp.databinding.ActivityMainBinding
import com.example.gitdemoapp.viewmodel.MainViewModel
import com.example.model.RepoModel
import java.util.Locale
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainViewModel
    private lateinit var adapter: MainAdapter
    private var repoList = ArrayList<RepoModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[MainViewModel::class.java]

        model.repoList.observe(this){
            repoList = it as ArrayList
            adapter = MainAdapter(it, this)
            binding.mainRecyclerview.adapter = adapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

        })

        return true
    }

    /**
     * Filter function to filter the list
     * in adapter
     **/
    private fun filter(text: String?) {
        val filteredList = ArrayList<RepoModel>()

        for (item in repoList) {
            if (item.repositoryName.lowercase().contains(text!!.lowercase(Locale.getDefault()))) {
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()) {
            adapter.filterList(filteredList)
        }
    }


}