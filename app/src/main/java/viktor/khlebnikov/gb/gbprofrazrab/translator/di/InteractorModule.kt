package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Repository
import viktor.khlebnikov.gb.gbprofrazrab.translator.interactor.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}