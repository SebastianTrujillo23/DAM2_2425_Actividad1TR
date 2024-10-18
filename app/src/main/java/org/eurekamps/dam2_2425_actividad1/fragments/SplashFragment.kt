package org.eurekamps.dam2_2425_actividad1.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_2425_actividad1.HomeActivity
import org.eurekamps.dam2_2425_actividad1.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    Log.d("SplashFragment", "Current User: $currentUser") // Agrega esto para verificar

    Handler(Looper.getMainLooper()).postDelayed({
        val action = if (currentUser != null) {
            Log.d("SplashFragment", "User is logged in, navigating to HomeActivity") // Log para verificar
            R.id.action_splashFragment_to_homeActivity
        } else {
            Log.d("SplashFragment", "User is not logged in, navigating to LoginFragment") // Log para verificar
            R.id.action_splashFragment_to_loginFragment
        }
        findNavController().navigate(action)
    }, 2000) // Esperar 2 segundos
}


    }

