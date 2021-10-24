package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local

import io.reactivex.Observable
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSource

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}