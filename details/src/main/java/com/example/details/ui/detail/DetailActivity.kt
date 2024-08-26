package com.example.details.ui.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.core.domain.model.UserDataModel
import com.example.details.databinding.ActivityDetailBinding
import com.example.pcsgroup.base.BaseActivity
import com.example.pcsgroup.util.Constant
import com.example.pcsgroup.util.parcelable

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    private val data by lazy {
        intent.parcelable<UserDataModel>(Constant.Intent.EXTRA_DETAIL_DATA)
    }

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        initView()
    }

    private fun initView() {
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(data?.avatar)
                .into(ivAvatar)
            tvFirstName.text = data?.firstName
            tvLastName.text = data?.lastName
            tvAddress.text = data?.address
        }
    }

    private fun setupToolbar() {
        binding.apply {
            toolBar.setNavigationOnClickListener {
                finish()
            }
        }
    }
}