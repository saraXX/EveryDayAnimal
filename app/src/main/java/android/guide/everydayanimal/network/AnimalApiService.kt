package android.guide.everydayanimal.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://zoo-animal-api.herokuapp.com"
private const val ENDPOINT = "/animals/rand/10"

// setup client side
val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

// send GET request
interface AnimalApiService{
    @GET(ENDPOINT)
    suspend fun getAnimals(): List<Animal>
}
// singleton object -> i only need one retrofit object | to use it over all project files
object AnimalApi{
    val retrofitService: AnimalApiService by lazy{
        retrofit.create(AnimalApiService::class.java)
    }
}