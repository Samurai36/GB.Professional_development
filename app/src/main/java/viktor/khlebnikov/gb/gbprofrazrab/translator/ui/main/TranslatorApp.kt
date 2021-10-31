package viktor.khlebnikov.gb.gbprofrazrab.translator.ui.main

import android.app.Application
import org.koin.core.context.startKoin
import viktor.khlebnikov.gb.gbprofrazrab.translator.di.application
import viktor.khlebnikov.gb.gbprofrazrab.translator.di.mainScreen

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }

}
