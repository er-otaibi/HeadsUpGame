package com.example.headupgameretrofit

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    lateinit var tvname : TextView
    lateinit var tvtaboo1 :TextView
    lateinit var tvtaboo2 :TextView
    lateinit var tvtaboo3 :TextView
    lateinit var showText :TextView
    var timer=60000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title="Timer:--"

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val rotation= resources.configuration.orientation

        if(rotation== Configuration.ORIENTATION_LANDSCAPE){
            tvname=findViewById(R.id.tvname)
            tvtaboo1=findViewById(R.id.tvtaboo1)
            tvtaboo2=findViewById(R.id.tvtaboo2)
            tvtaboo3=findViewById(R.id.tvtaboo3)
            apiInterface!!.getUsers().enqueue(object: Callback<List<HeadsUpDetaillsItem>>{


                override fun onResponse(call: Call<List<HeadsUpDetaillsItem>>, response: Response<List<HeadsUpDetaillsItem>>
                ) {
                    val list = response.body()!!
                    randomFun(list)
                }
                override fun onFailure(call: Call<List<HeadsUpDetaillsItem>>, t: Throwable) {
                    call.cancel()
                }
            })


        }
        else{
            showText=findViewById(R.id.textView)
            var startbtn=findViewById<Button>(R.id.startbtn)
            startbtn.isVisible=true
            showText.isVisible=true

            startbtn.setOnClickListener {
                showText.text="Rotate The Device ! "
                countTimer()

            }


        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Timer", timer)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        timer= savedInstanceState.getLong("Timer", 60000L)
        title= "Timer:${timer / 1000}"
        countTimer()
    }

    private fun countTimer(){

        object: CountDownTimer(timer, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer = millisUntilFinished
                title = "Timer ${timer / 1000}"
            }

            override fun onFinish() {
                title="TIME IS OUT!"

            }
        }.start()


    }
    fun randomFun(list: List<HeadsUpDetaillsItem>){

        var random = Random.nextInt(0,list.size)

        var randomobject = list[random]
        tvname.text= randomobject.name
        tvtaboo1.text=randomobject.taboo1
        tvtaboo2.text=randomobject.taboo2
        tvtaboo3.text=randomobject.taboo3

    }
}