package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import dagger.Module
import dagger.Provides
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataModel
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.DataSource
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.Repository
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.RepositoryImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.DataSourceLocal
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.local.RoomDataBaseImplementation
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote.DataSourceRemote
import viktor.khlebnikov.gb.gbprofrazrab.translator.data.remote.RetrofitImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote:
        DataSource<List<DataModel>>
    ): Repository<List<DataModel>> {
        return RepositoryImplementation(dataSourceRemote)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal:
        DataSource<List<DataModel>>
    ): Repository<List<DataModel>> {
        return RepositoryImplementation(dataSourceLocal)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> {
        return RetrofitImplementation()
    }


    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> {
        return RoomDataBaseImplementation()
    }
}