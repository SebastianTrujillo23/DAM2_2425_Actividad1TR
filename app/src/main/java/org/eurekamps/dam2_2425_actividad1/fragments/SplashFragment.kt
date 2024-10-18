package org.eurekamps.dam2_2425_actividad1.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_2425_actividad1.HomeActivity
import org.eurekamps.dam2_2425_actividad1.R


class SplashFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Comprobar si el usuario está logueado
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Usuario está logueado, redirigir a HomeActivity
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }, 2000) // 2 segundos de espera
        } else {
            // Usuario no está logueado, redirigir a LoginFragment
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.loginFragment)
            }, 2000) // 2 segundos de espera
        }
    }
}

