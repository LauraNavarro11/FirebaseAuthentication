package com.laura.firelogin.ui.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.laura.firelogin.databinding.MainActivityBinding
import com.laura.firelogin.ui.detail.DetailActivity
import com.laura.firelogin.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val splashViewModel :MainViewModel by viewModels()
    private lateinit var binding:MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        when(splashViewModel.checkDestination()){
            MainViewModel.SplashDestination.home -> navigateToHome()
            MainViewModel.SplashDestination.login -> navigateToLogin()
        }
    }

    //en caso de estar logeados iremos al detalle

    private fun navigateToHome() {
        startActivity(Intent(this,DetailActivity::class.java))
    }

    private fun navigateToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}