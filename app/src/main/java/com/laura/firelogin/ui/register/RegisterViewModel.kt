package com.laura.firelogin.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laura.firelogin.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authService: AuthService):ViewModel() {

    private val _isLoading= MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun register(email: String, password: String,navigateToDetail:() -> Unit) {
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val result = withContext(Dispatchers.IO) {

                    authService.register(email, password)
                }
                if (result!=null){
                    navigateToDetail()
                }
                else{
                    Log.i("error","error")
                }
            }
            catch (e:Exception){
                Log.i("laura", e.message.orEmpty())

            }

            _isLoading.value=false
        }

    }
}