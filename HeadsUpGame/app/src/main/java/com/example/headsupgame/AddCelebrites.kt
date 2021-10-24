package com.example.headsupgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddCelebrites : AppCompatActivity() {
    lateinit var et_name : EditText
    lateinit var et_toboo1 : EditText
    lateinit var et_toboo2 : EditText
    lateinit var et_toboo3 : EditText
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


        addBtn.setOnClickListener {
            name2 = et_name.text.toString()
            toboo_1 = et_toboo1.text.toString()
            toboo_2 = et_toboo2.text.toString()
            toboo_3 = et_toboo3.text.toString()
            var helper = DBHelper(applicationContext)
            var status = helper.savedat(name2, toboo_1 , toboo_2 , toboo_3)
            Toast.makeText(applicationContext, "New Celebrity is added Successfully\n $status" , Toast.LENGTH_SHORT).show()
            clearText()
        }
    }
    private fun clearText() {
        et_name.setText("")
        et_toboo1.setText("")
        et_toboo2.setText("")
        et_toboo3.setText("")
    }
}