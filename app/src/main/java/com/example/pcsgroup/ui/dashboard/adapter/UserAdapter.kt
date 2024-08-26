package com.example.pcsgroup.ui.dashboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.UserDataModel
import com.example.pcsgroup.databinding.ItemListUserBinding
import com.example.pcsgroup.util.toDateFormat

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val listUser: MutableList<UserDataModel> = mutableListOf()
    private var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    fun setData(newData: List<UserDataModel>) {
        this.listUser.addAll(newData)
        notifyItemRangeInserted(0, newData.size)
    }

    fun setupListener(listener: Listener) {
        this.listener = listener
    }

    inner class ViewHolder(
        private val binding: ItemListUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        @SuppressLint("SetTextI18n")
        fun bind(data: UserDataModel) {
            binding.apply {
                Glide.with(context)
                    .load(data.avatar)
                    .into(ivAvatar)

                tvName.text = "${data.firstName} ${data.lastName}"
                tvDob.text = data.dateOfBirth.toDateFormat(f = "dd MMM yyyy")
                root.setOnClickListener {
                    listener?.onItemClicked(data)
                }
            }
        }

    }

    interface Listener {
        fun onItemClicked(data: UserDataModel)
    }

}