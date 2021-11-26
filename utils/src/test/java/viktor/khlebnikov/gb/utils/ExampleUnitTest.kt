package viktor.khlebnikov.gb.utils

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isNotCorrect() {
        assertNotEquals(5, 2 + 2)
    }

    @Test
    fun assertNull() {
        assertNull(null)
    }

    @Test
    fun assertNotNull() {
        assertNotNull("notNull")
    }

    var array = arrayOf(3, 1, 2)

    @Test
    fun assertArrayEquals() {
        assertArrayEquals(arrayOf(1, 2, 3), array.sortedArray())
    }

    @Test
    fun assertSame() {
        assertSame("same", "sa" + "me")
    }
}