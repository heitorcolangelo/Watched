package com.heitorcolangelo.presentation.common.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.heitorcolangelo.presentation.factory.TestItemUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModelFactory
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PagedLiveDataTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val pagedLiveData = PagedLiveData<TestItemUiModel>()

    @Test
    fun when_postedValue_isNot_nullOrEmpty_then_appendValueToCurrentList() {
        val listSize = 3
        val items = TestItemUiModelFactory.makeList(listSize)

        pagedLiveData.postValue(items)

        assertEquals(listSize, pagedLiveData.value!!.size)
    }

    @Test
    fun when_postedValue_is_nullOrEmpty_then_postEmptyList() {
        val listSize = 3
        val items = TestItemUiModelFactory.makeList(listSize)
        pagedLiveData.postValue(items)

        pagedLiveData.postValue(null)

        assertEquals(0, pagedLiveData.value!!.size)
    }
}
