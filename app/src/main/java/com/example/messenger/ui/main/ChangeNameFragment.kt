package com.example.messenger.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messenger.R
import com.example.messenger.databinding.FragmentChangeNameBinding
import com.example.messenger.ui.main.auth.CHILD_FIRSTNAME
import com.example.messenger.ui.main.auth.CHILD_LASTNAME
import com.example.messenger.ui.main.auth.NODE_USERS
import com.example.messenger.ui.main.auth.REF_DATABASE_ROOT
import com.example.messenger.ui.main.auth.UID
import com.example.messenger.ui.main.auth.USER

class ChangeNameFragment : Fragment() {

    private var _binding: FragmentChangeNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChangeNameBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (requireActivity() as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onResume() {
        super.onResume()

        binding.changeNameFragmentFirstNameField.setText(USER.firstname)
        binding.changeNameFragmentLastNameField.setText(USER.lastname)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> {

                val firstName = binding.changeNameFragmentFirstNameField.text.toString()
                val lastName = binding.changeNameFragmentLastNameField.text.toString()
                if (firstName.isEmpty()) {
                    Toast.makeText(requireContext(), "Введите имя", Toast.LENGTH_SHORT).show()
                } else {
                    firstNameUpdate(firstName)
                    lastNameUpdate(lastName)
                    fragmentManager?.popBackStack()
                    Toast.makeText(requireContext(), "Данные успешно обновлены", Toast.LENGTH_SHORT)
                        .show()

                }


            }
        }
        return true
    }

    private fun firstNameUpdate(firstName: String) {

        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FIRSTNAME)
            .setValue(firstName).addOnCompleteListener {
                if (it.isSuccessful) {
                    USER.firstname = firstName
                }
            }
    }


    private fun lastNameUpdate(lastName: String) {

        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_LASTNAME)
            .setValue(lastName).addOnCompleteListener {
                if (it.isSuccessful) {
                    USER.lastname = lastName
                }
            }
    }


}