package ort.tp3.activities

import ort.tp3.adapters.CharacterAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ort.tp3.R
import ort.tp3.services.CharacterService
import ort.tp3.services.CharactersWrapper
import ort.tp3.services.RetrofitInstance
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CharacterAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            fetchCharacters()
        }
    }

    private suspend fun fetchCharacters() {
        val service: CharacterService = RetrofitInstance
            .getRetrofitInstance()
            .create(CharacterService::class.java)

        // Hacemos la llamada a la API, seteamos por defecto 4000 personajes
        val response: Response<CharactersWrapper> = service.getCharacters(4000)

        if (response.isSuccessful) {
            val characters = response.body()?.results

            // Si la lista de personajes no es nula, la seteamos en el adapter
            characters?.let {
                adapter.setCharacterList(it)
            }
        } else {
            println(response.errorBody())
        }
    }
}
