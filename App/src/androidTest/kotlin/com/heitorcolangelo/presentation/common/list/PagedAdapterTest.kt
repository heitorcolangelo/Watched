package com.heitorcolangelo.presentation.common.list

import android.view.ViewGroup
import com.heitorcolangelo.presentation.common.model.PageUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModelFactory
import com.heitorcolangelo.presentation.factory.TestViewHolder
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PagedAdapterTest {

    private val listener: PagedAdapter.PaginationListener = mockk(relaxed = true)
    private val adapter = TestPagedAdapter(listener)

    @Test
    fun when_isLastItem_and_isNotLoadingPage_then_getItemViewType_requestNewPage() {
        val list = TestItemUiModelFactory.makeList(3)
        adapter.submitList(list.toMutableList())
        val lastPosition = adapter.itemCount - 1

        adapter.getItemViewType(lastPosition)

        verify { listener.requestPage() }
    }

    @Test
    fun when_submitPage_then_setLoadingPageTo_false() {
        val list = TestItemUiModelFactory.makeList(3)
        val pageUiModel = PageUiModel(list, null)
        adapter.submitPage(pageUiModel)
        val lastPosition = adapter.itemCount - 1
        adapter.getItemViewType(lastPosition)

        adapter.submitPage(PageUiModel(listOf(), null))

        assertFalse(adapter.isLoadingPage())
    }

    @Test
    fun when_submitNotEmptyPage_andNoErrors_then_appendListAtTheEnd() {
        assertTrue(adapter.currentList.isEmpty())

        val list = TestItemUiModelFactory.makeList(3)
        val pageUiModel = PageUiModel(list, null)
        adapter.submitPage(pageUiModel)

        assertTrue(adapter.currentList.isNotEmpty())
        assertTrue(adapter.currentList.size == list.size)
    }

    @Test
    fun when_submitEmptyPage_andNoErrors_then_clearList() {
        val list = TestItemUiModelFactory.makeList(3)
        val pageUiModel = PageUiModel(list, null)
        adapter.submitPage(pageUiModel)

        assertTrue(adapter.currentList.isNotEmpty())
        assertTrue(adapter.currentList.size == list.size)

        adapter.submitPage(PageUiModel(listOf(), null))

        Thread.sleep(300L) // submitList in ListAdapter is async.
        assertTrue(adapter.currentList.isEmpty())
    }

    @Test
    fun when_requestNewPage_then_setLoadingPageTo_true() {
        val list = TestItemUiModelFactory.makeList(3)
        adapter.submitList(list.toMutableList())
        val lastPosition = adapter.itemCount - 1

        adapter.getItemViewType(lastPosition)

        assertTrue(adapter.isLoadingPage())
    }

    private class TestPagedAdapter(
        paginationListener: PaginationListener
    ) : PagedAdapter<TestItemUiModel, TestViewHolder>(paginationListener) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            return TestViewHolder(parent.rootView)
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        }
    }
}
