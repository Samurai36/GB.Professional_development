package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.DataModel

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
    override suspend fun saveToDB(appState: AppState) {
        return dataSource.saveToDB(appState)
    }
}