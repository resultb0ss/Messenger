package com.example.messenger.ui.main.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.messenger.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginFragmentFloatingActionButton.setOnClickListener {
            val phone = binding.loginFragmentNumberPhoneEditTextField.text.toString()
            if (phone.isEmpty()) {
                Toast.makeText(requireContext(), "Введите номер", Toast.LENGTH_SHORT).show()
            } else {
                val action = LoginFragmentDirections.Companion.actionLoginFragmentToEnterSmsFragment(phone)
                findNavController().navigate(action)
            }
        }


    }

//    private fun login() {
//        val email = binding.emailET.text.toString()
//        val pass = binding.passwordET.text.toString()
//
//        if (email.isNotEmpty() && pass.isNotEmpty()) {
//            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(requireActivity()) {
//                if (it.isSuccessful) {
//                    myToast("Успешный вход в систему")
//                    findNavController().navigate(R.id.baseFragment)
//                } else {
//                    myToast("Не удалось войти в систему")
//                    binding.redirectSignUpTV.visibility = View.VISIBLE
//                }
//            }
//        } else {
//            Toast.makeText(requireContext(), "Заполните необходимые поля", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}