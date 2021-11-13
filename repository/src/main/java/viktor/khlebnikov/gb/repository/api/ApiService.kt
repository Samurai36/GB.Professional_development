package viktor.khlebnikov.gb.repository.api

import retrofit2.http.GET
import retrofit2.http.Query
import viktor.khlebnikov.gb.model.DataModel

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}