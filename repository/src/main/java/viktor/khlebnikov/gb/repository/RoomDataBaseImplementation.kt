package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.dto.SearchResultDTO
import viktor.khlebnikov.gb.repository.room.HistoryDao

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<SearchResultDTO>> {

    override suspend fun getData(word: String): List<SearchResultDTO> {
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