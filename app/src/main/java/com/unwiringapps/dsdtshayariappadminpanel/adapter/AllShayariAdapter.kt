package com.unwiringapps.dsdtshayariappadminpanel.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

import com.unwiringapps.dilsedimagtak_shayariapp.Model.ShayariModel


import com.unwiringapps.dsdtshayariappadminpanel.AllShayariActivity
import com.unwiringapps.dsdtshayariappadminpanel.R
import com.unwiringapps.dsdtshayariappadminpanel.databinding.ItemShayariBinding


class AllShayariAdapter(
    val allShayariActivity: AllShayariActivity,
    val shayarilistrr: ArrayList<ShayariModel>,
    val catid: String
) : RecyclerView.Adapter<AllShayariAdapter.ShayariViewHolder>() {


    val db = FirebaseFirestore.getInstance()

    class ShayariViewHolder(val binding: ItemShayariBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayariViewHolder {
        return ShayariViewHolder(
            ItemShayariBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShayariViewHolder, position: Int) {
        holder.binding.itemShayari.text = shayarilistrr[position].data.toString()


        if (position % 5 == 0) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_1)
        } else if (position % 5 == 1) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_2)
        } else if (position % 5 == 2) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_3)
        } else if (position % 5 == 3) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_4)

        } else if (position % 5 == 4) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_5)
        } else if (position % 5 == 5) {
            holder.binding.ituu1.setBackgroundResource(R.drawable.gradient_6)
        }


        holder.binding.btnDelete.setOnClickListener {

            db.collection("tanmayshayari").document(catid).collection("all")
                .document(shayarilistrr[position].id!!).delete().addOnSuccessListener {
                    Toast.makeText(allShayariActivity,"Shayari Deleted Succesfully",Toast.LENGTH_LONG).show()
                }

        }


    }

    override fun getItemCount() = shayarilistrr.size


}
