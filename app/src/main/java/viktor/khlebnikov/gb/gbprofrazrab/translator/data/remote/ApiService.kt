package viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel

interface ApiService {

    @GET("words/search")
    suspend fun search(@Query("search") wordToSearch: String): List<DataModel>
}