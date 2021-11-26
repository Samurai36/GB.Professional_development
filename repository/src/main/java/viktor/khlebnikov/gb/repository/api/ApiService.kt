package viktor.khlebnikov.gb.repository.api

import retrofit2.http.GET
import retrofit2.http.Query
import viktor.khlebnikov.gb.model.dto.SearchResultDTO

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<SearchResultDTO>
}