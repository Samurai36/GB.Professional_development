package viktor.khlebnikov.gb.repository

import viktor.khlebnikov.gb.model.AppState

interface Repository<T> {
    suspend fun getData(word: String): T
}

interface DataSource<T> {
    suspend fun getData(word: String): T
}

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}