package com.greemoid.githubmessanger.presentation.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.databinding.ActivityMainBinding
import com.greemoid.githubmessanger.presentation.core.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java, this)

        val listener: (item: MenuItem) -> Boolean = {
            viewModel.changeScreen(it.itemId)
            true
        }

        viewModel.observe(this) {
            binding.bottomNavView.setOnItemSelectedListener(null)
            binding.bottomNavView.selectedItemId = it.id
            val fragment = viewModel.getFragment(it.id)
            //if (supportFragmentManager.canReplace(fragment)) doesn't work with this line
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            binding.bottomNavView.setOnItemSelectedListener(listener)
        }
        viewModel.init()
    }
}

//private fun FragmentManager.canReplace(fragment: BaseFragment<*>) =
//    fragments.isEmpty() || !(fragments.last() as BaseFragment<*>).matches(fragment.name())