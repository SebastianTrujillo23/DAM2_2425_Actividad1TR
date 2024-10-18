package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import org.eurekamps.dam2_2425_actividad1.FBProfile.ProfileDataHolder
import org.eurekamps.dam2_2425_actividad1.R
import org.eurekamps.dam2_2425_actividad1.singletone.Profile
import com.google.firebase.database.FirebaseDatabase






class ProfileFragment : Fragment() {
    private var currentProfile: Profile? = null // Variable para almacenar el perfil
    private lateinit var database: DatabaseReference // Referencia a la base de datos de Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Inicializar Firebase Database
        database = FirebaseDatabase.getInstance().getReference("Profiles")

        // Referencias a los EditText y botones
        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val ageEditText = view.findViewById<EditText>(R.id.editTextAge)
        val birthdateEditText = view.findViewById<EditText>(R.id.editTextBirthdate)
        val hobbiesEditText = view.findViewById<EditText>(R.id.editTextHobbies)
        val saveButton = view.findViewById<Button>(R.id.buttonSave)
        val logoutButton = view.findViewById<Button>(R.id.buttonLogout)

        // Manejar el clic en el botón "Guardar"
        saveButton.setOnClickListener {
            // Validar la entrada de la edad
            val ageInput = ageEditText.text.toString()
            val age = if (ageInput.isNotEmpty()) {
                ageInput.toInt()
            } else {
                Toast.makeText(requireContext(), "Por favor, ingrese una edad válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Salir de la función si la entrada no es válida
            }

            // Crear el objeto Profile
            currentProfile = Profile(
                name = nameEditText.text.toString(),
                age = age,
                birthdate = birthdateEditText.text.toString(),
                hobbies = hobbiesEditText.text.toString()
            )

            // Guardar el perfil en Firebase
            currentProfile?.let { profile ->
                val profileId = database.push().key // Generar un ID único para el perfil
                if (profileId != null) {
                    database.child(profileId).setValue(profile)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Perfil guardado", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Error al guardar el perfil", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }

        // Manejar el clic en el botón "Cerrar sesión"
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut() // Cerrar sesión en Firebase
            Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show()
            // Aquí podrías navegar a otro fragmento o activity si lo deseas
        }

        return view
    }
}





