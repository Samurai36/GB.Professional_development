package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.dto.SearchResultDTO


class RepositoryImplementationRemote(private val dataSource: DataSource<List<SearchResultDTO>>) :
    Repository<List<SearchResultDTO>> {

    override suspend fun getData(word: String): List<SearchResultDTO> {
        return dataSource.getData(word)
    }
}