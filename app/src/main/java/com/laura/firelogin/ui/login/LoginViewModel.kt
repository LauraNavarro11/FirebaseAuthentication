package com.laura.firelogin.ui.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laura.firelogin.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( private val authService: AuthService):ViewModel() {
    private val _isLoading= MutableStateFlow<Boolean>(false)
    val isLoading:StateFlow<Boolean> = _isLoading

    fun login(user:String,password:String,navigateToDetail:() -> Unit){
        viewModelScope.launch{
            _isLoading.value=true
            val result= withContext(Dispatchers.IO){
                authService.login(user,password)

            }
            if (result!= null){
                navigateToDetail()

            }
            else{
                //error
            }
            _isLoading.value=false

        }

    }

}