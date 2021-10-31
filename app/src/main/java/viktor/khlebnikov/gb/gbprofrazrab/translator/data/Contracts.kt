package viktor.khlebnikov.gb.gbprofrazrab.translator.data


interface View {
    fun renderData(appState: AppState)
}

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}

interface Repository<T> {
    suspend fun getData(word: String): T
}

interface DataSource<T> {
    suspend fun getData(word: String): T
}