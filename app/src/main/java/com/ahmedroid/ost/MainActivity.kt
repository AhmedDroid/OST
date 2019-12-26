package com.ahmedroid.ost

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedroid.data.State
import com.ahmedroid.data.repositories.WeatherRepoImpl
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel: WeatherCardViewModel by lazy {
        WeatherViewModelProvider(
            application as App,
            WeatherRepoImpl((application as App).weatherService)
        ).create(WeatherCardViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        searchWith(DEF_CITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_RC) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val place = Autocomplete.getPlaceFromIntent(data)
                searchWith(place.name ?: DEF_CITY)
            }
        }
    }

    private fun initViews() {
        setSupportActionBar(mainActivityToolbar)

        mainActivityRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        searchImageView.setOnClickListener {
            startSearchByCity()
        }
    }

    private fun startSearchByCity() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME)
        val intent = Autocomplete.IntentBuilder(
            AutocompleteActivityMode.OVERLAY, fields
        ).build(this)
        startActivityForResult(intent, RESULT_RC)
    }

    private fun searchWith(city: String) {
        mainViewModel?.getWeatherAt(city)?.observe(this, Observer {
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

    private companion object {
        private const val RESULT_RC = 99
        private const val DEF_CITY = "Amman"
    }
}
