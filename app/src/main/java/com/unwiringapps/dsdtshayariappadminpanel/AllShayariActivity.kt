package com.unwiringapps.dsdtshayariappadminpanel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.dilsedimagtak_shayariapp.Model.ShayariModel
import com.unwiringapps.dsdtshayariappadminpanel.adapter.AllShayariAdapter

import com.unwiringapps.dsdtshayariappadminpanel.databinding.ActivityAllShayariBinding

class AllShayariActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllShayariBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllShayariBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")


        binding.catText1.text = name.toString()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


        db = FirebaseFirestore.getInstance()

        db.collection("tanmayshayari").document(id!!).collection("all").addSnapshotListener { value, error ->

            val shayarilistrr  = arrayListOf<ShayariModel>()
            val shayaridatarr = value?.toObjects(ShayariModel::class.java)
            shayarilistrr.addAll(shayaridatarr!!)


            binding.rcvallS.layoutManager = LinearLayoutManager(this)
            binding.rcvallS.adapter = AllShayariAdapter(this, shayarilistrr)
        }




    }

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


    }
}