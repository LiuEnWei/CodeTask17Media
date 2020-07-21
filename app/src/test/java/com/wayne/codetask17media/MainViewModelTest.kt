package com.wayne.codetask17media

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.wayne.codetask17media.di.viewModelModule
import com.wayne.codetask17media.view.main.MainViewModel
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest: KoinTest {

    private val mainViewModel: MainViewModel by inject()
    @Mock lateinit var loadingObserver: Observer<Boolean>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin{
            modules(viewModelModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun startLoadingTest() {
        mainViewModel.isLoading.observeForever(loadingObserver)
        mainViewModel.startLoading()
        verify(loadingObserver).onChanged(true)
    }

    @Test
    fun stopLoading() {
        mainViewModel.isLoading.observeForever(loadingObserver)
        mainViewModel.stopLoading()
        verify(loadingObserver).onChanged(false)
    }
}