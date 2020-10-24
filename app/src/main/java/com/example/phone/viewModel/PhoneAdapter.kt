package com.example.phone.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phone.R
import com.example.phone.local.RealPhone
import kotlinx.android.synthetic.main.phone_list_view.view.*

class PhoneAdapter( val mPassTheData: PassTheData): RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {
    private var phoneinList = emptyList<RealPhone>()
    fun updateAdapter(mList: List<RealPhone>) {
        phoneinList = mList
        notifyDataSetChanged()

    }

    inner class PhoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.tv1
        val itemImage = itemView.imageView
        val name = itemView.tv2
        val price = itemView.tv3
        val clickListener = itemView.setOnClickListener {
            mPassTheData.passTheData(phoneinList[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.phone_list_view, parent, false)
        return PhoneViewHolder(itemView)
    }
    override fun getItemCount() = phoneinList.size

    interface PassTheData {
        fun passTheData(phone: RealPhone)

    }
    override fun onBindViewHolder(holder: PhoneViewHolder,position: Int) {
        val phone = phoneinList[position]
        Glide.with(holder.itemView.context).load(phone.image).into(holder.itemImage)
        holder.name.text = phone.name
        holder.price.text = phone.price.toString()

    }
}