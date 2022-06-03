package com.unwiringapps.dsdtshayariappadminpanel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

import com.unwiringapps.dilsedimagtak_shayariapp.Model.CatModel
import com.unwiringapps.dsdtshayariappadminpanel.AllShayariActivity

import com.unwiringapps.dsdtshayariappadminpanel.MainActivity
import com.unwiringapps.dsdtshayariappadminpanel.R
import com.unwiringapps.dsdtshayariappadminpanel.databinding.ItemCatBinding

class CategoryAdapter(val mainActivity: MainActivity, val listrr: ArrayList<CatModel>) :
    RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

//    val colorLister =
//        arrayListOf<String>(
//            "#2193b0",
//            "#cc2b5e",
//            "#ee9ca7",
//            "#42275a",
//            "#de6262",
//            "#06beb6",
//            "#000000"
//        )
//    val colorLister1 =
//        arrayListOf<String>(
//            "#E2F516",
//            "#FBF6D9",
//            "#ee9ca7",
//            "#EE82EE",
//            "#DCD0FF",
//            "#FFF5EE",
//            "#ee9ca7"
//        )



    val db = FirebaseFirestore.getInstance()

    class CatViewHolder(val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {


        if (position % 5 == 0) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 1) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_2)
        } else if (position % 5 == 2) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_3)
        } else if (position % 5 == 3) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_4)

        } else if (position % 5 == 4) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_5)
        } else if (position % 5 == 5) {
            holder.binding.itemTxt.setBackgroundResource(R.drawable.gradient_6)
        }

//
//        if (position % 7 == 0) {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[0]))
//        } else if (position % 7 == 1)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[1]))
//        }
//        else if (position % 7 == 2)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[2]))
//        }
//
//        else if (position % 7 == 3)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[3]))
//
//        }
//        else if (position % 7 == 4)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[4]))
//        }
//
//        else if (position % 7 == 5)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[5]))
//        }
//        else if (position % 7 == 6)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[6]))
//        }
//
//        else if (position % 7 == 7)
//        {
//            holder.binding.itemTxt.setBackgroundColor(Color.parseColor(colorLister1[7]))
//        }





        holder.binding.itemTxt.text = listrr[position].name.toString()
//        holder.binding.rcvCard.setBackgroundColor(Color.parseColor(colorLister[position]))
//        holder.binding.rcvCard.setCardBackgroundColor(Color.parseColor(colorLister[position]))
//        holder.binding.itemTxt.setTextColor(Color.parseColor(colorLister1[position]))

        holder.binding.root.setOnClickListener {

            val intent = Intent(mainActivity, AllShayariActivity::class.java)

            intent.putExtra("id", listrr[position].id)
            intent.putExtra("name", listrr[position].name)

            mainActivity.startActivity(intent)


            holder.binding.btnDelete.setOnClickListener {

                db.collection("tanmayshayari").document(listrr[position].id!!).delete().addOnSuccessListener {
                    Toast.makeText(mainActivity," ${listrr[position].name.toString()} CATEGORY DELETED SUCCESFULLY",Toast.LENGTH_LONG).show()
                }

            }


        }

    }

    override fun getItemCount() = listrr.size
}