package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.DataModel
import viktor.khlebnikov.gb.repository.room.HistoryDao

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