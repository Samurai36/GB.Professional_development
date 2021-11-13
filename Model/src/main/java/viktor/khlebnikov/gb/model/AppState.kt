package viktor.khlebnikov.gb.model

sealed interface AppState {
    data class Success(val data: List<DataModel>?) : AppState
    data class Error(val error: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}