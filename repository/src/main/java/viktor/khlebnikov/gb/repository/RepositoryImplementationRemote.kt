package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.DataModel

class RepositoryImplementationRemote(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}