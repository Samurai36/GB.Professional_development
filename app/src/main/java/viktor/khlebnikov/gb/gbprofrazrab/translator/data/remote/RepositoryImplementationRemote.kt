package viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote

import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSource
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Repository

class RepositoryImplementationRemote(private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}