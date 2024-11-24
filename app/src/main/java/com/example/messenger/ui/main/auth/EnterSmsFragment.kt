package com.example.messenger.ui.main.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.messenger.R
import com.example.messenger.databinding.FragmentEnterSmsBinding

class EnterSmsFragment : Fragment() {

    private var _binding: FragmentEnterSmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentEnterSmsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phone = EnterSmsFragmentArgs.Companion.fromBundle(requireArguments()).phone
        binding.smsEnterFragmentSubtitleText.text =
            "Мы направили сообщение с смс кодом на номер $phone"


    }

    override fun onStart() {
        super.onStart()
        binding.smsEnterFragmentNumberPhoneEditTextField.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length == 6) {
                    goToEnterPassFragment()
                }
            }
        })
    }

    private fun goToEnterPassFragment() {
        findNavController().navigate(R.id.action_enterSmsFragment_to_enterPassFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}