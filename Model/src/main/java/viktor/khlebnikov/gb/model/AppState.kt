package viktor.khlebnikov.gb.model

import viktor.khlebnikov.gb.model.usersData.DataModel

sealed interface AppState {
    data class Success(val data: List<DataModel>?) : AppState
    data class Error(val error: Throwable) : AppState
    data class Loading(val progress: Int? = null) : AppState
}