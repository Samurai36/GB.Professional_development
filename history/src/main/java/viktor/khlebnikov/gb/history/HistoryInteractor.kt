package viktor.khlebnikov.gb.history

import viktor.khlebnikov.gb.core.viewmodel.Interactor
import viktor.khlebnikov.gb.model.AppState
import viktor.khlebnikov.gb.model.dto.SearchResultDTO
import viktor.khlebnikov.gb.repository.Repository
import viktor.khlebnikov.gb.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<SearchResultDTO>>,
    private val repositoryLocal: RepositoryLocal<List<SearchResultDTO>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            mapSearchResultToResult(
                if (fromRemoteSource) {
                    repositoryRemote
                } else {
                    repositoryLocal
                }.getData(word)
            )
        )
    }
}