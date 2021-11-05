package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import androidx.room.Room
import org.koin.dsl.module
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.*
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.RepositoryImplementationLocal
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.RoomDataBaseImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history.HistoryDataBase
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history.HistoryInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.history.HistoryViewModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote.RepositoryImplementationRemote
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote.RetrofitImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>> {
        RepositoryImplementationRemote(RetrofitImplementation())
    }

    single<DataSource<List<DataModel>>> {
        RetrofitImplementation()
    }

    single<RepositoryLocal<List<DataModel>>>{
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