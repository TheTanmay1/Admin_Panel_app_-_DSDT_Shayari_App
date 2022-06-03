package com.unwiringapps.dsdtshayariappadminpanel

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.dilsedimagtak_shayariapp.Model.CatModel
import com.unwiringapps.dilsedimagtak_shayariapp.Model.ShayariModel
import com.unwiringapps.dsdtshayariappadminpanel.adapter.AllShayariAdapter

import com.unwiringapps.dsdtshayariappadminpanel.databinding.ActivityAllShayariBinding
import com.unwiringapps.dsdtshayariappadminpanel.databinding.DialogAddCatBinding
import com.unwiringapps.dsdtshayariappadminpanel.databinding.DialogAddShayariBinding

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


//        binding.catText1.text = name.toString()

//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }


        db = FirebaseFirestore.getInstance()

        db.collection("tanmayshayari").document(id!!).collection("all")
            .addSnapshotListener { value, error ->

                val shayarilistrr = arrayListOf<ShayariModel>()
                val shayaridatarr = value?.toObjects(ShayariModel::class.java)
                shayarilistrr.addAll(shayaridatarr!!)


                binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
                binding.rcvAllShayari.adapter = AllShayariAdapter(this, shayarilistrr, id)
            }


        binding.toolbarTitle.text = name.toString()


        binding.btnAddShayari.setOnClickListener {
            val addCatDialog = Dialog(this@AllShayariActivity)
            val binding = DialogAddShayariBinding.inflate(layoutInflater)
//            addCatDialog.setContentView(R.layout.dialog_add_cat)
            addCatDialog.setContentView(binding.root)

            if (addCatDialog.window != null)              // TO MAKE DIALOG CORNERS CURLY
            {
                addCatDialog.window!!.setBackgroundDrawable(ColorDrawable(0))  // TO MAKE DIALOG CORNERS CURLY
            }


            binding.dialogBtnshaay.setOnClickListener {

                val uid = db.collection("tanmayshayari").document().id
                val edtShayariget = binding.dialogShayari.text.toString()

                val finalValue = ShayariModel(uid, edtShayariget)

                db.collection("tanmayshayari").document(id).collection("all").document(uid)
                    .set(finalValue).addOnCompleteListener {
                        if (it.isSuccessful) {
                            addCatDialog.dismiss()
                            Toast.makeText(this@AllShayariActivity, "Shayari Added Succesfully",Toast.LENGTH_LONG).show()
                        }
                    }


            }

            addCatDialog.show()


        }

    }

    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


    }
}