package com.example.pcsgroup.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.domain.model.UserDataModel
import com.example.core.domain.usecase.UserUseCase
import com.example.pcsgroup.base.BaseViewModel
import com.example.pcsgroup.util.Resources
import com.example.pcsgroup.util.scheduler.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    private val _userData: MutableLiveData<Resources<List<UserDataModel>>> = MutableLiveData()
    val userData: LiveData<Resources<List<UserDataModel>>> = _userData

    fun getData() {
        _userData.postValue(Resources.Loading())
        userUseCase.getData()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                if (it.isEmpty()) {
                    _userData.postValue(Resources.Empty())
                } else {
                    _userData.postValue(Resources.Success(it))
                }
            }, {
                _userData.postValue(Resources.Error(it))
            }).disposedBy(disposable)
    }

}