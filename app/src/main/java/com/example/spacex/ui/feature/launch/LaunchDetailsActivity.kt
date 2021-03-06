package com.example.spacex.ui.feature.launch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.spacex.MainActivity
import com.example.spacex.R
import com.example.spacex.databinding.ActivityLaunchDetailsBinding
import com.example.spacex.viewmodel.SpaceXViewModel
import org.koin.android.ext.android.inject

class LaunchDetailsActivity : AppCompatActivity() {
    private val viewModel: SpaceXViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_details)
        val binding: ActivityLaunchDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_launch_details)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.getRocketInfo(intent.getStringExtra(MainActivity.EXTRA_ROCKET_ID).orEmpty())
        viewModel.rocketResponseLiveData.observe(this, { response ->
            response.data?.let {
                it.body()?.let { rocketData ->
                    binding.data = rocketData
                    binding.launchDetails =
                        intent.getStringExtra(MainActivity.EXTRA_LAUNCH_DESCRIPTION).orEmpty()
                }
            }
        })
        viewModel.errorResponseLiveData.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}