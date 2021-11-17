package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainActivity
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainViewModel
import viktor.khlebnikov.gb.history.HistoryActivity
import viktor.khlebnikov.gb.history.HistoryInteractor
import viktor.khlebnikov.gb.history.HistoryViewModel
import viktor.khlebnikov.gb.model.dto.SearchResultDTO
import viktor.khlebnikov.gb.repository.*
import viktor.khlebnikov.gb.repository.room.HistoryDataBase

val application = module {
    single<Repository<List<SearchResultDTO>>> { RepositoryImplementationRemote(RetrofitImplementation()) }
    single<DataSource<List<SearchResultDTO>>> { RetrofitImplementation() }

    single<RepositoryLocal<List<SearchResultDTO>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
    single<DataSourceLocal<List<SearchResultDTO>>> { RoomDataBaseImplementation(get()) }

    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
}

val mainScreen = module {
    scope<MainActivity>{
        viewModel { MainViewModel(get()) }
        scoped { MainInteractor(get(), get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        viewModel { HistoryViewModel(get()) }
        scoped  { HistoryInteractor(get(), get()) }
    }
}