package com.laura.firelogin.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.laura.firelogin.R
import com.laura.firelogin.databinding.ActivityDetailBinding
import com.laura.firelogin.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel:DetailViewModel by viewModels()
    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListener()
    }

    private fun initListener() {
        binding.btnLogout.setOnClickListener{
            detailViewModel.logout(){navigateToLogin()}

        }
    }

    private fun navigateToLogin() {
        //borrar la pila para que al navegar hacia atras de login, no regrese a al detail
        startActivity(Intent(this,LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })

    }
}