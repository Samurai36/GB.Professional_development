package viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.AppState
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Interactor
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Repository

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) withContext(Dispatchers.IO) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}