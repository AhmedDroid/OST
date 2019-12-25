package com.ahmedroid.ost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedroid.data.State
import com.ahmedroid.data.repositories.WeatherRepoImpl
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewDataBinding: ViewDataBinding? = null

    private var mainViewModel: WeatherCardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mainActivityToolbar)

        mainActivityRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainViewModel = WeatherCardViewModel(
            WeatherRepoImpl(
                (application as App).weatherService,
                Realm.getDefaultInstance()
            )
        )
        mainViewModel?.getWeatherAt("Amman")?.observe(this, Observer {
            when (it) {
                is State.Loading -> {
                    mainActivitySwipeRefreshLayout.isRefreshing = it.isLoading
                }
                is State.Success -> {
                    mainActivityRecyclerView.adapter = WeatherAdapter(it.response)
                }
                is State.Error -> {
                    Toast.makeText(this, it.errMsg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
