package com.example.messenger.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.messenger.R
import com.example.messenger.databinding.FragmentSettingsBinding
import com.example.messenger.ui.main.auth.AUTHFIREBASE
import com.example.messenger.ui.main.auth.AuthActivity
import com.example.messenger.ui.main.auth.EnterSmsFragmentArgs
import com.example.messenger.ui.main.auth.NODE_USERS
import com.example.messenger.ui.main.auth.REF_DATABASE_ROOT
import com.example.messenger.ui.main.auth.UID
import com.example.messenger.ui.main.auth.USER
import com.example.messenger.ui.main.models.User
import com.example.messenger.ui.main.utils.AppValueEventListener

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var fullName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        initUser()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_fragment_action_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingFragmentMenuItemExit -> {
                AUTHFIREBASE.signOut()
                startActivity(Intent(requireActivity(), AuthActivity::class.java))
            }
            R.id.settingFragmentMenuItemChangeName -> findNavController().navigate(R.id.action_settingsFragment_to_changeNameFragment)
        }
        return true
    }

    @SuppressLint("SetTextI18n")
    fun initFields(){
        binding.settingsFragmentAboutTV.text = USER.bio
        binding.settingsFragmentUserNameTV.text = "${USER.firstname} ${USER.lastname}"
        binding.settingsFragmentPhoneNumberTV.text = USER.phone
        binding.settingsFragmentUserStatusTV.text = USER.status
        binding.settingsFragmentLoginTV.text = USER.username
        binding.settingsFragmentChangeLoginField.setOnClickListener{
            findNavController().navigate(R.id.action_settingsFragment_to_changeUserNameFragment)
        }
    }

    private fun initUser(){
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).addListenerForSingleValueEvent(
            AppValueEventListener{
                USER = it.getValue(User::class.java) ?: User()
            }
        )
        initFields()
    }


}