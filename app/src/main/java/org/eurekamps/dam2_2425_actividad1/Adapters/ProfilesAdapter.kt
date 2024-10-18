package org.eurekamps.dam2_2425_actividad1.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.eurekamps.dam2_2425_actividad1.R
import org.eurekamps.dam2_2425_actividad1.singletone.Profile

class ProfilesAdapter(private val profilesList: List<Profile>) :
    RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profilesList[position]
        holder.bind(profile)
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

        fun bind(profile: Profile) {
            nameTextView.text = profile.name // Mostrar el nombre del perfil
        }
    }
}
