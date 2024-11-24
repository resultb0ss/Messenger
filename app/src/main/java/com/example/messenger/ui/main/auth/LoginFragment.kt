package com.example.messenger.ui.main.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.messenger.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                AUTHFIREBASE.signInWithCredential(p0).addOnCompleteListener {
                    if (it.isSuccessful) {
                        customToast("Вы успешно зашли в систему")
                    } else {
                        customToast(it.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                customToast(p0.message.toString())
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                val phone = binding.loginFragmentNumberPhoneEditTextField.text.toString()
                val id = p0
                val action =
                    LoginFragmentDirections.Companion.actionLoginFragmentToEnterSmsFragment(
                        phone,
                        id
                    )
                findNavController().navigate(action)
            }

        }

        binding.loginFragmentFloatingActionButton.setOnClickListener {
            val phone = binding.loginFragmentNumberPhoneEditTextField.text.toString()
            if (phone.isEmpty()) {
                customToast("Заполните номер телефона")
            } else {
                login()
            }
        }
    }

    fun login() {

        val phone = binding.loginFragmentNumberPhoneEditTextField.text.toString()
        val options = PhoneAuthOptions.newBuilder(AUTHFIREBASE)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun customToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}