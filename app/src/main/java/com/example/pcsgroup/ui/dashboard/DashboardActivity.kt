package com.example.pcsgroup.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.core.domain.model.UserDataModel
import com.example.pcsgroup.R
import com.example.pcsgroup.base.BaseActivity
import com.example.pcsgroup.databinding.ActivityDashboardBinding
import com.example.pcsgroup.ui.dashboard.adapter.UserAdapter
import com.example.pcsgroup.util.Constant
import com.example.pcsgroup.util.Resources
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    private val viewModel: DashboardViewModel by viewModels()
    private val adapter by lazy { UserAdapter() }

    override fun getViewBinding(): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
        initObserver()
        setupAdapter()
    }

    private fun initObserver() {
        viewModel.userData.observe(this) { model ->
            when (model) {
                is Resources.Loading -> {
                    showLoading()
                }

                is Resources.Success -> {
                    showRecyclerView(model.data)
                }

                is Resources.Empty -> {
                    showEmptyLayout()
                }

                is Resources.Error -> {
                    showError(model.throwable)
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@DashboardActivity)
            rvUser.adapter = adapter

            adapter.setupListener(listener = object : UserAdapter.Listener {
                override fun onItemClicked(data: UserDataModel) {
                    startActivity(
                        Intent(
                            this@DashboardActivity,
                            Class.forName(Constant.ReflectionClassName.DETAILS_CLASS_NAME)
                        ).apply {
                            putExtra(Constant.Intent.EXTRA_DETAIL_DATA, data)
                        }
                    )
                }
            })
        }
    }

    private fun showLoading() {
        binding.apply {
            lottieAnimation.isVisible = true
        }
    }

    private fun showRecyclerView(data: List<UserDataModel>?) {
        binding.apply {
            lottieAnimation.isVisible = false
            rvUser.isVisible = true
            adapter.setData(data.orEmpty())
        }
    }

    private fun showEmptyLayout() {
        binding.apply {
            lottieAnimation.isVisible = false
            emptyErrorLayout.isVisible = true
        }
    }

    private fun showError(throwable: Throwable?) {
        binding.apply {
            lottieAnimation.isVisible = false
            emptyErrorLayout.isVisible = true
            Glide.with(this@DashboardActivity)
                .load(R.drawable.ic_error)
                .into(ivFailed)
            tvFailed.text = getString(R.string.error_list, throwable?.message)
        }
    }

}