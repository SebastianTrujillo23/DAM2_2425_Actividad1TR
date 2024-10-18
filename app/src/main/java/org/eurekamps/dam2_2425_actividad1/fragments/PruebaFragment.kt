package org.eurekamps.dam2_2425_actividad1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.eurekamps.dam2_2425_actividad1.Adapters.ProfilesAdapter
import org.eurekamps.dam2_2425_actividad1.R

import org.eurekamps.dam2_2425_actividad1.singletone.Profile

class PruebaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var profileAdapter: ProfilesAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prueba, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.profiles_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        database = FirebaseDatabase.getInstance().getReference("Profiles")
        loadProfiles()
    }

    private fun loadProfiles() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profilesList = mutableListOf<Profile>()
                for (profileSnapshot in snapshot.children) {
                    val profile = profileSnapshot.getValue(Profile::class.java)
                    profile?.let { profilesList.add(it) }
                }
                profileAdapter = ProfilesAdapter(profilesList)
                recyclerView.adapter = profileAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de errores
            }
        })
    }
}

