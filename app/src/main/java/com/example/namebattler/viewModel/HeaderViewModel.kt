package com.example.namebattler.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.namebattler.util.HeaderFlag

class HeaderViewModel: ViewModel() {

    var headerText: MutableLiveData<String> = MutableLiveData()
    var outputFlag:HeaderFlag = HeaderFlag.NONE

}