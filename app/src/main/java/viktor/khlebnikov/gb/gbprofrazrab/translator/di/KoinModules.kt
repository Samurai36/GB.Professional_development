package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Repository
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.RepositoryImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.RoomDataBaseImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote.RetrofitImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}