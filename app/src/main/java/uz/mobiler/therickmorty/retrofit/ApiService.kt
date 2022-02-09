package uz.mobiler.therickmorty.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.mobiler.therickmorty.models.Character

interface ApiService {
    @GET("character/")
    fun getCharacters() : Call<Character>
}