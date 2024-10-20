package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import org.eurekamps.dam2_2425_actividad1.R
import org.eurekamps.dam2_2425_actividad1.adapters.ProfilesAdapter
import org.eurekamps.dam2_2425_actividad1.singletone.Profile

class PruebaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var profilesAdapter: ProfilesAdapter
    private val db = FirebaseFirestore.getInstance()
    private val profilesList = mutableListOf<Profile>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prueba, container, false)

        recyclerView = view.findViewById(R.id.profiles_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        profilesAdapter = ProfilesAdapter(profilesList)
        recyclerView.adapter = profilesAdapter

        loadProfiles()

        return view
    }

    private fun loadProfiles() {
        db.collection("profiles")
            .get()
            .addOnSuccessListener { documents ->
                profilesList.clear()  // Limpiar la lista antes de agregar nuevos perfiles
                for (document in documents) {
                    val profile = document.toObject(Profile::class.java)
                    profilesList.add(profile)
                }
                profilesAdapter.notifyDataSetChanged()  // Notificar al adaptador que los datos han cambiado
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error al cargar perfiles: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}




