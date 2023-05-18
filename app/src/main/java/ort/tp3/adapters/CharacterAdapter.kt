package ort.tp3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ort.tp3.R
import ort.tp3.dataclasses.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characterList: List<Character> = emptyList()

    fun setCharacterList(characters: List<Character>) {
        characterList = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]

        holder.bind(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.characterImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.characterNameTextView)

        fun bind(character: Character) {
            // Cargamos la imagen con Glide
            Glide.with(itemView.context)
                .load(character.picture.thumbnail)
                .into(imageView)

            // Seteamos el nombre completo
            val fullName = "${character.name.first} ${character.name.last}"
            nameTextView.text = fullName
        }
    }
}
