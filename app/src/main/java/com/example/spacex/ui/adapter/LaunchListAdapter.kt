package com.example.spacex.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.spacex.MainActivity
import com.example.spacex.data.network.response.LaunchResponseItem
import com.example.spacex.databinding.ItemLaunchBinding
import com.example.spacex.ui.feature.launch.LaunchDetailsActivity

class LaunchListAdapter() :
    RecyclerView.Adapter<LaunchListAdapter.ViewHolder>() {
    private var mList = ArrayList<LaunchResponseItem>()
    private var mTempList = ArrayList<LaunchResponseItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = ViewHolder(binding)
        if (viewType != NO_POSITION) {
            val item = mList[viewType]
            viewHolder.binding.mainContainer.setOnClickListener {
                Intent(parent.context, LaunchDetailsActivity::class.java).apply {
                    putExtra(MainActivity.EXTRA_LAUNCH_DESCRIPTION, item.details)
                    putExtra(MainActivity.EXTRA_ROCKET_ID, item.rocket?.rocketId)
                    parent.context.startActivity(this)
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = mList[position]
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(val binding: ItemLaunchBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<LaunchResponseItem>) {
        mList = list
        mTempList = mList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterByLaunchSuccess() {
        val f = mTempList.filter { it.launchSuccess == true }
        mList = (f as ArrayList<LaunchResponseItem>)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterByLaunchUnSuccess() {
        val f = mTempList.filter { it.launchSuccess == false }
        mList = (f as ArrayList<LaunchResponseItem>)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortByMissionName() {
        mList.sortBy { it.missionName }
        notifyDataSetChanged()
    }

}