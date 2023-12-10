package com.laura.firelogin.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.laura.firelogin.databinding.ActivityRegisterBinding
import com.laura.firelogin.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private val singUpViewModel:RegisterViewModel by viewModels()
    private lateinit var binding:ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListener()
        initUIstate()
    }

    private fun initUIstate() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                singUpViewModel.isLoading.collect {
                    binding.loading.isVisible = it
                }
            }
        }
    }

    private fun initListener() {
        binding.btnSignUp.setOnClickListener{
            singUpViewModel.register(
                email=binding.tieUser.text.toString(),
                password=binding.tiePassword.text.toString()

            ){navigateToDetail()}
        }

    }

    private fun navigateToDetail() {
        startActivity(Intent(this,DetailActivity::class.java))
    }
}