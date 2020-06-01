package com.heitorcolangelo.presentation.common.list

import android.view.ViewGroup
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
    fun when_submitList_then_setLoadingPageTo_false() {
        val list = TestItemUiModelFactory.makeList(3)
        adapter.submitList(list.toMutableList())
        val lastPosition = adapter.itemCount - 1
        adapter.getItemViewType(lastPosition)

        adapter.submitList(mutableListOf())

        assertFalse(adapter.isLoadingPage())
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
