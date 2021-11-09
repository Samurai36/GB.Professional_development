package viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main

import viktor.khlebnikov.gb.gbprofrazrab.translator.data.*

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appstate: AppState
        if (fromRemoteSource) {
            appstate = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appstate)
        } else {
            appstate = AppState.Success(localRepository.getData(word))
        }
        return appstate
    }
}