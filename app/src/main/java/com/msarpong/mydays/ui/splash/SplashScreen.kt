package com.msarpong.mydays.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.msarpong.mydays.R
import com.msarpong.mydays.ui.main.MainScreen
import java.util.*
import kotlin.concurrent.schedule

const val MAIN_SCREEN_TIME = 2000L

class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        goToMain()
    }

    private fun goToMain() {
        val runnableOnboarding = Runnable {
            val main = Intent(this, MainScreen::class.java)
            startActivity(main)

            finish()
        }
        Handler().postDelayed(runnableOnboarding, MAIN_SCREEN_TIME)
    }


}

