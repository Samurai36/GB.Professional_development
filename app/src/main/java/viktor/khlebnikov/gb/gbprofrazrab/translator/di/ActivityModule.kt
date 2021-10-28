package viktor.khlebnikov.gb.gbprofrazrab.translator.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}