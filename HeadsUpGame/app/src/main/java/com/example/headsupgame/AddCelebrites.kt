package com.example.headsupgame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.graphics.toColorInt
import com.example.headsupgame.MyList.mylist

object MyList {

    var mylist = arrayListOf<Celebrities>()
}

class AddCelebrites : AppCompatActivity() {
    lateinit var et_name : EditText
    lateinit var et_toboo1 : EditText
    lateinit var et_toboo2 : EditText
    lateinit var et_toboo3 : EditText
    lateinit var textView: TextView
    lateinit var addBtn : Button
    var name2 =""
    var toboo_1 =""
    var toboo_2 =""
    var toboo_3 =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celebrites)

        et_name = findViewById(R.id.et_name)
        et_toboo1 = findViewById(R.id.et_toboo1)
        et_toboo2 = findViewById(R.id.et_toboo2)
        et_toboo3 = findViewById(R.id.et_toboo3)
        addBtn = findViewById(R.id.addBtn)
        textView = findViewById(R.id.textView)
        
       var helper = DBHelper(applicationContext)


        addBtn.setOnClickListener {
            name2 = et_name.text.toString()
            toboo_1 = et_toboo1.text.toString()
            toboo_2 = et_toboo2.text.toString()
            toboo_3 = et_toboo3.text.toString()

            var status = helper.savedat(name2, toboo_1 , toboo_2 , toboo_3)
            Toast.makeText(applicationContext, "New Celebrity is added Successfully\n $status" , Toast.LENGTH_SHORT).show()

            var i = helper.getNote(name2)
            //updateList()
            textView.append("${i.name}\n${i.taboo1}\n ${i.taboo2}\n ${i.taboo3}\n\n")
            clearText()
        }
        mylist.clear()
        helper.readData()
        updateList()
    }
    private fun clearText() {
        et_name.setText("")
        et_toboo1.setText("")
        et_toboo2.setText("")
        et_toboo3.setText("")
    }

    private fun updateList(){

        for (i in mylist ){
            textView.setTextColor(Color.RED)
            textView.append("${i.name}\n${i.taboo1}\n ${i.taboo2}\n ${i.taboo3}\n\n")
        }
    }
}