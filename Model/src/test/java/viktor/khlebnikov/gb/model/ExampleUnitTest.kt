package viktor.khlebnikov.gb.model

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun appState_NotSame() {
        assertNotSame(AppState.Loading(), AppState.Error(Throwable()))
    }
}