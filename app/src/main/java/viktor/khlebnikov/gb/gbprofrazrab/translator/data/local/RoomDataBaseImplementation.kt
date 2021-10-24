package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local

import io.reactivex.Observable
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSource

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented")
    }
}