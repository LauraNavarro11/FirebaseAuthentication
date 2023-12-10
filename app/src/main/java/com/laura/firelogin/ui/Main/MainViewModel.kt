package com.laura.firelogin.ui.Main

import androidx.lifecycle.ViewModel
import com.laura.firelogin.data.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val authService: AuthService):ViewModel(){
    private fun isUserLogged():Boolean{
        return authService.isUserLogged()
    }


    fun checkDestination():SplashDestination{
        val isUserLogged=isUserLogged()
        return if(isUserLogged){
            SplashDestination.home
        }
        else{
            SplashDestination.login
        }

    }
    sealed class SplashDestination(){
        object home:SplashDestination()
        object login:SplashDestination()
    }
}