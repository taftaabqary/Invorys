package com.astradigital.invorys

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.astradigital.invorys.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
        setupAnimation()
    }

    private fun setupAnimation() {
        val translationAnimator = ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_Y, 0f, -50f).apply {
            duration = 2000
        }

        val alphaAnimator = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA,  1f).apply {
            duration = 2000
        }

        val circle1 = ObjectAnimator.ofFloat(binding.circle1, View.ALPHA, 1f).apply {
            duration = 2000
        }

        val circle2 = ObjectAnimator.ofFloat(binding.circle2, View.ALPHA, 1f).apply {
            duration = 2000
        }

        val textLogo = ObjectAnimator.ofFloat(binding.tvApp, View.ALPHA, 1f).apply {
            duration = 2000
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(translationAnimator, alphaAnimator, textLogo, circle1, circle2)
        animatorSet.start()
    }

    private fun setupAction() {
        lifecycleScope.launch(Dispatchers.IO) {
            delay(TIME_OUT)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@MainActivity, HostActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }

    companion object {
        private const val TIME_OUT: Long = 3000
    }
}