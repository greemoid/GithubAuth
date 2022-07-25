package com.greemoid.githubmessanger.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.greemoid.githubmessanger.R
import com.greemoid.githubmessanger.databinding.FragmentMyProfileBinding
import com.greemoid.githubmessanger.presentation.core.BaseActivity
import com.greemoid.githubmessanger.presentation.main.BaseFragment
import com.greemoid.githubmessanger.sl.core.Feature


class MyProfileFragment : BaseFragment<MyProfileViewModel>() {

    override val titleResId: Int = R.string.title_profile

    override fun viewModelClass(): Class<MyProfileViewModel> = MyProfileViewModel::class.java

    override fun name(): String {
        return Feature.MY_PROFILE.name
    }

    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(this) {
            it.map(binding.nameTextview, binding.loginTextView, binding.avatarImageView)
        }

        binding.signOutButton.setOnClickListener {
            viewModel.signOut()
            (requireActivity() as BaseActivity).switchToLogin()
        }

        viewModel.init()
    }

}