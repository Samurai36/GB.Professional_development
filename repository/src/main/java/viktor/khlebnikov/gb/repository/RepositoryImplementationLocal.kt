package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.dto.SearchResultDTO

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDTO>>) :
    RepositoryLocal<List<SearchResultDTO>> {
    override suspend fun getData(word: String): List<SearchResultDTO> {
        return dataSource.getData(word)
    }
    override suspend fun saveToDB(appState: AppState) {
        return dataSource.saveToDB(appState)
    }
}