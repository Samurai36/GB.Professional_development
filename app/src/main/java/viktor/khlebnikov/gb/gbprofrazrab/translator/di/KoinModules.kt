package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import androidx.room.Room
import org.koin.dsl.module
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainViewModel
import viktor.khlebnikov.gb.history.HistoryInteractor
import viktor.khlebnikov.gb.history.HistoryViewModel
import viktor.khlebnikov.gb.model.DataModel
import viktor.khlebnikov.gb.repository.*
import viktor.khlebnikov.gb.repository.room.HistoryDataBase

val application = module {
    single<Repository<List<DataModel>>> {
        RepositoryImplementationRemote(RetrofitImplementation())
    }

    single<DataSource<List<DataModel>>> {
        RetrofitImplementation()
    }

    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }

    single<DataSourceLocal<List<DataModel>>> {
        RoomDataBaseImplementation(get())
    }
    
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}