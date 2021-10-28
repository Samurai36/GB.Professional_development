package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.TranslatorApp
import javax.inject.Singleton

@Component(
    modules = [
        ActivityModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TranslatorApp)

}