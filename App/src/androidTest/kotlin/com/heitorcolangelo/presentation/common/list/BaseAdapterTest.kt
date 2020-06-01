package com.heitorcolangelo.presentation.common.list

import android.view.ViewGroup
import com.heitorcolangelo.presentation.factory.TestItemUiModel
import com.heitorcolangelo.presentation.factory.TestViewHolder
import org.junit.Assert.assertTrue
import org.junit.Test

class BaseAdapterTest {

    @Test
    fun when_onItemClicked_Then_setItemClickListener() {
        val testAdapter = TestAdapter()

        testAdapter.onItemClicked {
        }

        assertTrue(testAdapter.isItemClickSet())
    }

    private class TestAdapter : BaseAdapter<TestItemUiModel, TestViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TestViewHolder {
            return TestViewHolder(parent.rootView)
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        }

        fun isItemClickSet(): Boolean {
            return itemClickListener != null
        }
    }
}
