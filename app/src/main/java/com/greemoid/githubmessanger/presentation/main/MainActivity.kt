package com.greemoid.githubmessanger.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.databinding.ActivityMainBinding
import com.greemoid.githubmessanger.presentation.core.BaseActivity
import com.greemoid.githubmessanger.presentation.login.LoginActivity

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("GMSharedPref", Context.MODE_PRIVATE)
        val profile = sharedPreferences.getString("profile", "")
        Firebase.auth.currentUser?.let {
            val text = it.displayName + "\n" + profile
            binding.textView.text = text
            Glide.with(this).load(it.photoUrl).into(binding.avatarImageView)
        }

        binding.signOutButton.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}