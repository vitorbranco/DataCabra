package com.vitorxbranco.datacabra

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor

@ExperimentalCoroutinesApi
class CoroutineTestRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(testDispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    fun pauseDispatcher() {
        (testDispatcher as? TestCoroutineDispatcher)?.pauseDispatcher()
    }

    fun resumeDispatcher() {
        (testDispatcher as? TestCoroutineDispatcher)?.resumeDispatcher()
    }

    fun advanceTimeBy(delayMillis: Long) {
        (testDispatcher as? TestCoroutineDispatcher)?.advanceTimeBy(delayMillis)
    }

    fun advanceUntilIdle() {
        (testDispatcher as? TestCoroutineDispatcher)?.advanceUntilIdle()
    }
}