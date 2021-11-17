package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.dto.SearchResultDTO
import viktor.khlebnikov.gb.repository.room.HistoryEntity

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDTO> {
    val searchResult = ArrayList<SearchResultDTO>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(SearchResultDTO(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text, null)
            }
        }
        else -> null
    }
}