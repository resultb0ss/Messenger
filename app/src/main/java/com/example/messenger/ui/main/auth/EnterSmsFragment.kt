package com.example.messenger.ui.main.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.messenger.databinding.FragmentEnterSmsBinding
import com.example.messenger.ui.main.MainActivity
import com.google.firebase.auth.PhoneAuthProvider

class EnterSmsFragment : Fragment() {

    private var _binding: FragmentEnterSmsBinding? = null
    private val binding get() = _binding!!

    private lateinit var idForCredential: String
    private lateinit var phone: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentEnterSmsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phone = EnterSmsFragmentArgs.Companion.fromBundle(requireArguments()).phone
        idForCredential = EnterSmsFragmentArgs.Companion.fromBundle(requireArguments()).id

        binding.smsEnterFragmentSubtitleText.text =
            "Мы направили сообщение с смс кодом на номер $phone"


    }

    override fun onStart() {
        super.onStart()
        binding.smsEnterFragmentSMSEditTextField.addTextChangedListener(object :
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

        val smsCode = binding.smsEnterFragmentSMSEditTextField.text.toString()
        val credential = PhoneAuthProvider.getCredential(idForCredential, smsCode)

        AUTHFIREBASE.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                customToast("Вы успешно авторизованы")
                startActivity(Intent(requireActivity(), MainActivity::class.java))
            } else customToast(it.exception?.message.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun customToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

}