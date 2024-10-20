package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.eurekamps.dam2_2425_actividad1.R
import org.eurekamps.dam2_2425_actividad1.singletone.Profile

class ProfileFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var birthdateEditText: EditText
    private lateinit var hobbiesEditText: EditText

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        nameEditText = view.findViewById(R.id.nameEditText)
        ageEditText = view.findViewById(R.id.ageEditText)
        birthdateEditText = view.findViewById(R.id.birthdateEditText)
        hobbiesEditText = view.findViewById(R.id.hobbiesEditText)

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            saveProfile()
        }

        val logoutButton = view.findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logoutUser()
        }

        return view
    }

    private fun saveProfile() {
        val name = nameEditText.text.toString().trim()
        val age = ageEditText.text.toString().toIntOrNull()
        val birthdate = birthdateEditText.text.toString().trim()
        val hobbies = hobbiesEditText.text.toString().trim()

        findNavController().navigate(R.id.action_profileFragment_to_pruebaFragment2)


        if (name.isEmpty() || age == null || birthdate.isEmpty() || hobbies.isEmpty()) {
            Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val profile = Profile(name, age, birthdate, hobbies)

        val userId = auth.currentUser?.uid
        userId?.let {
            db.collection("profiles").document(it)
                .set(profile)
                .addOnSuccessListener {
                    Toast.makeText(context, "Perfil guardado exitosamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun logoutUser() {
        auth.signOut()
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }
}

