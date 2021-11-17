package viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main

import viktor.khlebnikov.gb.core.viewmodel.Interactor
import viktor.khlebnikov.gb.gbprofrazrab.translator.utils.mapSearchResultToResult
import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.dto.SearchResultDTO
import viktor.khlebnikov.gb.repository.Repository
import viktor.khlebnikov.gb.repository.RepositoryLocal

class MainInteractor(
    private val remoteRepository: Repository<List<SearchResultDTO>>,
    private val localRepository: RepositoryLocal<List<SearchResultDTO>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appstate: AppState
        if (fromRemoteSource) {
            appstate = AppState.Success(mapSearchResultToResult(remoteRepository.getData(word)))
            localRepository.saveToDB(appstate)
        } else {
            appstate = AppState.Success(mapSearchResultToResult(localRepository.getData(word)))
        }
        return appstate
    }
}