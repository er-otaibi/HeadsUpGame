package com.example.headsupgame

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Surface
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var llTop: LinearLayout
    private lateinit var llMain: LinearLayout
    private lateinit var llCelebrity: LinearLayout

    private lateinit var tvTime: TextView

    private lateinit var tvName: TextView
    private lateinit var tvTaboo1: TextView
    private lateinit var tvTaboo2: TextView
    private lateinit var tvTaboo3: TextView

    private lateinit var tvMain: TextView
    private lateinit var btStart: Button
    private lateinit var btAdd: Button

    var gameActive = false
    private lateinit var celebrities: ArrayList<JSONObject>

    private var celeb = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llTop = findViewById(R.id.llTop)
        llMain = findViewById(R.id.llMain)
        llCelebrity = findViewById(R.id.llCelebrity)

        tvTime = findViewById(R.id.tvTime)

        tvName = findViewById(R.id.tvName)
        tvTaboo1 = findViewById(R.id.tvTaboo1)
        tvTaboo2 = findViewById(R.id.tvTaboo2)
        tvTaboo3 = findViewById(R.id.tvTaboo3)

        tvMain = findViewById(R.id.tvMain)
        btStart = findViewById(R.id.btStart)
        btAdd = findViewById(R.id.button)

        var helper = DBHelper(applicationContext)
        helper.readData()

        btStart.setOnClickListener { getData()  }
        btAdd.setOnClickListener {
            val intent = Intent(this, AddCelebrites::class.java)
            startActivity(intent)
        }

        celebrities = arrayListOf()

    }

    private fun getData(){

        MyList.mylist.shuffle()
        getCelebrity(0)
        newTimer()
    }
    private fun getCelebrity(id: Int) {
        if (id < MyList.mylist.size) {
            tvName.text = MyList.mylist[id].name
            tvTaboo1.text = MyList.mylist[id].taboo3
            tvTaboo2.text = MyList.mylist[id].taboo2
            tvTaboo3.text = MyList.mylist[id].taboo3

        }

    }

    private fun newTimer() {
        if (!gameActive) {
            gameActive = true
            tvMain.text = "Please Rotate Device"
            btStart.isVisible = false
            btAdd.isVisible = false
            val rotation = windowManager.defaultDisplay.rotation
            if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
                updateStatus(false)
            } else {
                updateStatus(true)
            }

            object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTime.text = "Time: ${millisUntilFinished / 1000}"
                }

                override fun onFinish() {
                    gameActive = false
                    tvTime.text = "Time: --"
                    tvMain.text = "Heads Up!"
                    btStart.isVisible = true
                    btAdd.isVisible = true
                    updateStatus(false)
                }
            }.start()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val rotation = windowManager.defaultDisplay.rotation
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            if (gameActive) {
                celeb++
                getCelebrity(celeb)
                updateStatus(false)
            } else {
                updateStatus(false)
            }
        } else {
            if (gameActive) {
                updateStatus(true)
            } else {
                updateStatus(false)
            }
        }
    }

    private fun updateStatus(showCelebrity: Boolean){
        if(showCelebrity){
            llCelebrity.isVisible = true
            llMain.isVisible = false

        }else{
            llCelebrity.isVisible = false
            llMain.isVisible = true


        }
    }

}


