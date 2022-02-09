package uz.mobiler.therickmorty.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.mobiler.therickmorty.models.Character
import uz.mobiler.therickmorty.retrofit.ApiClient

class AppViewModel :ViewModel() {
    private  val TAG = "AppViewModel"
    private var liveData=MutableLiveData<Character>()
    fun getResult():LiveData<Character>{
        ApiClient.apiService.getCharacters().enqueue(object :Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                Log.d(TAG, "onResponse: ${response.body()?.info?.pages}")
                if (response.isSuccessful){
                    liveData.value= response.body()
                    Log.d(TAG, "onResponse: ${response.body()!!.results.size}")
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return liveData
    }
}