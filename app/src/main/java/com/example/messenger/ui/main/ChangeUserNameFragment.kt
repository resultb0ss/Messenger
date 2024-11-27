package com.example.messenger.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.messenger.R
import com.example.messenger.databinding.FragmentChangeUserNameBinding
import com.example.messenger.ui.main.auth.CHILD_USERNAME
import com.example.messenger.ui.main.auth.NODE_USERNAMES
import com.example.messenger.ui.main.auth.NODE_USERS
import com.example.messenger.ui.main.auth.REF_DATABASE_ROOT
import com.example.messenger.ui.main.auth.UID
import com.example.messenger.ui.main.auth.USER
import com.example.messenger.ui.main.utils.AppValueEventListener
import com.example.messenger.ui.main.utils.getToast


class ChangeUserNameFragment : Fragment() {

    private var _binding: FragmentChangeUserNameBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNewUserName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChangeUserNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        binding.changeUserNameFragmentUsernameField.setText(USER.username)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (requireActivity() as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> {
                change()
                findNavController().navigate(R.id.action_changeUserNameFragment_to_settingsFragment)

            }
        }
        return true
    }

    private fun change() {

        mNewUserName = binding.changeUserNameFragmentUsernameField.text.toString().lowercase()

        if (mNewUserName.isEmpty()) {
            getToast("Заполните поле username", requireContext())
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES).addListenerForSingleValueEvent(
                AppValueEventListener {
                    if (it.hasChild(mNewUserName)) {
                        getToast("Такой пользователь уже есть", requireContext())
                    } else {
                        changeUserName()
                    }
                })

        }
    }

    private fun changeUserName() {

        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUserName).setValue(UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername()
                } else {
                    getToast(it.exception?.message.toString(), requireContext())
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME).setValue(mNewUserName)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    getToast("Данные успешно обновлены", requireContext())
                    deleteOldUsername()
                } else {
                    getToast(it.exception?.message.toString(), requireContext())
                }

            }
    }
    private fun deleteOldUsername(){
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue().addOnCompleteListener{
            if (it.isSuccessful){
                getToast("Данные успешно обновлены", requireContext())
                USER.username = mNewUserName
            } else {
                getToast(it.exception?.message.toString(), requireContext())
            }
        }
    }


}