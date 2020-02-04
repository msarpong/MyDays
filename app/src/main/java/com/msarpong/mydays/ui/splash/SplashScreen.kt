package com.msarpong.mydays.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import java.util.*
import kotlin.concurrent.schedule

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val main = Intent(this, MainScreen::class.java)

        Timer().schedule(3000) {
            startActivity(main)
            finish()
        }
    }


}

