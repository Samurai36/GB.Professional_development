package viktor.khlebnikov.gb.gbprofrazrab.translator.data.local

import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSourceLocal
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.RepositoryLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
    override suspend fun saveToDB(appState: AppState) {
        return dataSource.saveToDB(appState)
    }
}