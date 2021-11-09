package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local

import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSourceLocal
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history.HistoryDao
import viktor.khlebnikov.gb.gbprofrazrab.translator.utils.convertDataModelSuccessToEntity
import viktor.khlebnikov.gb.gbprofrazrab.translator.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
//        return historyDao.all().map(HistoryEntity::toDomainModel)
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
//        val entity = appState.toEntity() ?: return
//        historyDao.insert(entity)
//
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

}