package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.eurekamps.dam2_2425_actividad1.R


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // Comprobar si el usuario está logueado
        if (auth.currentUser == null) {
            // Mostrar mensaje o redirigir
            Toast.makeText(context, "Debes estar logueado para acceder a este perfil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.loginFragment)
        } else {
            // Aquí puedes agregar la lógica para mostrar y guardar el perfil
        }
    }
}
