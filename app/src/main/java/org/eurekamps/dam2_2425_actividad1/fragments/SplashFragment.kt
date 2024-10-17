package org.eurekamps.dam2_2425_actividad1.fragments

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
import org.eurekamps.dam2_2425_actividad1.R


class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var progressBar: ProgressBar
    private lateinit var splashImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Inicializar Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Inicializar los componentes de la interfaz
        progressBar = view.findViewById(R.id.progress_bar)
        splashImageView = view.findViewById(R.id.splash_image)

        // Lógica para el splash
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserStatus()
        }, 3000) // Espera 3 segundos

        return view
    }

    private fun checkUserStatus() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Usuario está logeado, verificar si tiene perfil
            checkUserProfile(currentUser.uid)
        } else {
            // Usuario no logeado
            navigateToLogin()
        }
    }

    private fun checkUserProfile(userId: String) {
        firestore.collection("Profiles").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Usuario logeado y con perfil
                    navigateToHome()
                } else {
                    // Usuario logeado y sin perfil
                    navigateToProfile()
                }
            }
            .addOnFailureListener {
                // Manejar errores
                Toast.makeText(requireContext(), "Error al verificar perfil", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateToLogin() {
        progressBar.visibility = View.GONE // Ocultar la barra de progreso
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun navigateToProfile() {
        progressBar.visibility = View.GONE // Ocultar la barra de progreso
        findNavController().navigate(R.id.action_splashFragment_to_profileFragment)
    }

    private fun navigateToHome() {
        progressBar.visibility = View.GONE // Ocultar la barra de progreso
        findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
    }
}
