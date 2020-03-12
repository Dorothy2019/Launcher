package com.example.launcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.launcher.adapter.LauncherPagerAdapter
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        vpLauncherPanels.adapter = LauncherPagerAdapter(supportFragmentManager)
    }
}
