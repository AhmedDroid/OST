package com.ahmedroid.ost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedroid.data.models.UpdateEvent
import com.ahmedroid.data.repositories.WeatherRepo
import com.ahmedroid.data.repositories.WeatherRepoImpl
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    private var viewDataBinding: ViewDataBinding? = null

    private var mainViewModel: WeatherCardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mainActivityToolbar)

        mainViewModel = WeatherCardViewModel(WeatherRepoImpl((application as App).weatherService, Realm.getDefaultInstance()))
        mainViewModel?.getWeatherAt("")
    }

    @Subscribe
    fun gets(updateEvent: UpdateEvent) {
        Log.d("test", "te")
    }
}
