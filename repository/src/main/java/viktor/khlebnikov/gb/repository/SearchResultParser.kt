package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.DataModel
import viktor.khlebnikov.gb.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): viktor.khlebnikov.gb.repository.room.HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                viktor.khlebnikov.gb.repository.room.HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}