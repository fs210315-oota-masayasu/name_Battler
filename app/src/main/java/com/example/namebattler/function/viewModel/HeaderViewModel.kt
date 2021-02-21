package com.example.namebattler.function.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.function.HeaderFlag

class HeaderViewModel: ViewModel() {

    var headerText: MutableLiveData<String> = MutableLiveData()
    var outputFlag: HeaderFlag = HeaderFlag.NONE

}