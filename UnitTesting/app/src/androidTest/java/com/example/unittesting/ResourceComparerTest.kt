package com.example.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceComparerTest {

    // BAD PRACTICE: This is not a good way to test Android resources.
    // Tests should be independent of each other.
    // private val resourceComparer = ResourceComparer()
    private lateinit var resourceComparer: ResourceComparer

    // @Before Runs this function before each test.
    // This is a good place to initialize variables instead of repeating code in each test.
    @Before
    fun setup() {
        resourceComparer = ResourceComparer()
    }

    @After
    fun teardown() {
        // This is a good place to clean up variables, close database connections, etc.
        // This is not necessary for this example.
    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        // resourceComparer = ResourceComparer()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(
            context,
            R.string.app_name,
            "UnitTesting",
        )
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        // resourceComparer = ResourceComparer()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(
            context,
            R.string.app_name,
            "Hello",
        )
        assertThat(result).isFalse()
    }
}
