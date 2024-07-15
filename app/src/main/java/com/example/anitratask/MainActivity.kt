package com.example.anitratask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anitratask.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        intiate the activity of this activity
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        this button is for navigation to the maps activity
        binding?.mapbutton?.setOnClickListener{
            val intent= Intent(this, MapsActivity:: class.java)
            startActivity(intent)

        }


    }
    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}