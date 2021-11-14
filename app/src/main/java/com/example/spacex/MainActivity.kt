package com.example.spacex

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.spacex.databinding.ActivityMainBinding
import com.example.spacex.ui.adapter.LaunchListAdapter
import com.example.spacex.utils.filterByLaunchSuccess
import com.example.spacex.utils.filterByLaunchUnSuccess
import com.example.spacex.utils.sortByMissionName
import com.example.spacex.viewmodel.SpaceXViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: SpaceXViewModel by inject()
    private lateinit var launchListAdapter: LaunchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getLaunchData()
        launchListAdapter = LaunchListAdapter()
        rvLaunch.apply {
            adapter = launchListAdapter
        }

        viewModel.launchListLiveData.observe(this, {
            launchListAdapter.updateList(it)
        })

        viewModel.errorResponseLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.filter_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.name -> {
                launchListAdapter.updateList(sortByMissionName(viewModel.filteredList))
                return true
            }
            R.id.launchSuccess -> {
                viewModel.filteredList = filterByLaunchSuccess(viewModel.launchList)
                launchListAdapter.updateList(viewModel.filteredList)
                return true
            }

            R.id.launchFail -> {
                viewModel.filteredList = filterByLaunchUnSuccess(viewModel.launchList)
                launchListAdapter.updateList(viewModel.filteredList)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_LAUNCH_DESCRIPTION = "extra_launch_description"
        const val EXTRA_ROCKET_ID = "extra_rocket_id"
    }
}