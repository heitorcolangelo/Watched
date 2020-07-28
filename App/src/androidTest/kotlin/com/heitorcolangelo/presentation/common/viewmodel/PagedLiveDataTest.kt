package com.heitorcolangelo.presentation.common.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heitorcolangelo.presentation.common.model.PageUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModelFactory
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PagedLiveDataTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val pagedLiveData = PagedLiveData<TestItemUiModel>()

    @Test
    fun when_postedValue_isNot_nullOrEmpty_then_appendValueToCurrentList() {
        val listSize = 3
        val items = TestItemUiModelFactory.makeList(listSize)
        PageUiModel(items, null)
        pagedLiveData.postValue(PageUiModel(items, null))

        assertEquals(listSize, pagedLiveData.value!!.items.size)
    }

    @Test
    fun when_refreshList_then_postEmptyList() {
        pagedLiveData.refreshList()

        assertEquals(0, pagedLiveData.value!!.items.size)
    }
}
