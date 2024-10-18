package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_2425_actividad1.FBProfile.ProfileDataHolder
import org.eurekamps.dam2_2425_actividad1.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        Log.d("SplashFragment", "Current User: $currentUser")

        Handler(Looper.getMainLooper()).postDelayed({
            val action = when {
                currentUser == null && ProfileDataHolder.profile == null -> R.id.action_splashFragment_to_loginFragment

                currentUser == null && ProfileDataHolder.profile != null -> R.id.action_splashFragment_to_loginFragment
                currentUser != null && ProfileDataHolder.profile != null -> R.id.action_splashFragment_to_homeActivity
                else -> R.id.action_splashFragment_to_loginFragment
            }
            findNavController().navigate(action)
        }, 2000)
    }
}


