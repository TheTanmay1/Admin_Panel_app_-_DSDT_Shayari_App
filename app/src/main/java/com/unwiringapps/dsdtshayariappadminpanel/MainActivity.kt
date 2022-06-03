package com.unwiringapps.dsdtshayariappadminpanel

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.unwiringapps.dilsedimagtak_shayariapp.Model.CatModel
import com.unwiringapps.dsdtshayariappadminpanel.adapter.CategoryAdapter
import com.unwiringapps.dsdtshayariappadminpanel.databinding.ActivityMainBinding
import com.unwiringapps.dsdtshayariappadminpanel.databinding.DialogAddCatBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = FirebaseFirestore.getInstance()

        db.collection("tanmayshayari").addSnapshotListener { value, error ->

            val listrr = arrayListOf<CatModel>()
            val datarr = value?.toObjects(CatModel::class.java)
            listrr.addAll(datarr!!)


            binding.rcvCategory.layoutManager = LinearLayoutManager(this)
            binding.rcvCategory.adapter = CategoryAdapter(this, listrr)
        }

        supportActionBar?.hide()

        binding.btnAddCat.setOnClickListener {

            val addCatDialog = Dialog(this@MainActivity)
            val binding = DialogAddCatBinding.inflate(layoutInflater)
//            addCatDialog.setContentView(R.layout.dialog_add_cat)
            addCatDialog.setContentView(binding.root)

            if (addCatDialog.window != null)              // TO MAKE DIALOG CORNERS CURLY

            {
                addCatDialog.window!!.setBackgroundDrawable(ColorDrawable(0))  // TO MAKE DIALOG CORNERS CURLY
            }


            binding.dialogBtncat.setOnClickListener {
                val name = binding.dialogCatname.text.toString()
                val id = db.collection("tanmayshayari").document().id
                val databro = CatModel(id,name)

                db.collection("tanmayshayari").document(id).set(databro).addOnSuccessListener {
                   Toast.makeText(this@MainActivity,"Shayari Category has been added Succesfully", Toast.LENGTH_LONG).show()
                    addCatDialog.dismiss()
                } .addOnCanceledListener {
                    Toast.makeText(this@MainActivity, "Shayari Category adding Failed$it", Toast.LENGTH_LONG).show()
// github uploaded
                }
            }

            addCatDialog.show()

        }

    }
}