package viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote

import io.reactivex.Observable
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSource
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.RoomDataBaseImplementation

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}