package viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}