package ort.tp3.services

import ort.tp3.dataclasses.Character
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

typealias Characters = List<Character>
// Creamos una clase wrapper para poder deserializar la respuesta de la API
class CharactersWrapper(val results: Characters)

interface CharacterService {
    @GET("/api")
    suspend fun getCharacters(@Query("results") results: Int): Response<CharactersWrapper>
}