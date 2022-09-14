package com.example.gitdemoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitdemoapp.R
import com.example.gitdemoapp.databinding.MainItemLayoutBinding
import com.example.model.RepoModel

/**
 * adapter for displaying the
 * unfiltered list of repo's
 **/
class MainAdapter(private var list: List<RepoModel> , private val ctx: Context): RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = MainItemLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = list[position]

           holder.descTextView.text = item.description
           holder.rankTextView.text = item.rank
           holder.repoNameTextView.text = item.repositoryName


        //selected item change background color
        if(item.selected){
            holder.card.setBackgroundResource(android.R.color.holo_blue_bright)
        }
        else{
            holder.card.setBackgroundResource(R.color.userDefined)
        }


        //on click listener to select item
        holder.card.setOnClickListener{
            item.selected = !item.selected
            notifyItemChanged(position)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: MainItemLayoutBinding): RecyclerView.ViewHolder(itemView.root){
        val rankTextView = itemView.rankNumber
        val repoNameTextView = itemView.repoName
        val descTextView = itemView.desc
        val card: CardView = itemView.card

    }


    // method for filtering our recyclerview items.
    fun filterList(filterList: List<RepoModel>) {
        list = filterList
        notifyDataSetChanged()
    }
}