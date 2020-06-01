package com.heitorcolangelo.presentation.common.list

import com.heitorcolangelo.presentation.factory.TestItemUiModel
import com.heitorcolangelo.presentation.factory.TestItemUiModelFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DiffItemCallbackTest {
    private val diffCallback = DiffItemCallback<TestItemUiModel>()

    @Test
    fun `WHEN items ids are the same THEN areItemsTheSame returns TRUE`() {
        val oldItem = TestItemUiModelFactory.make()
        val newItem = oldItem.copy()
        assertTrue(diffCallback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `WHEN items ids are different THEN areItemsTheSame returns FALSE`() {
        val oldItem = TestItemUiModelFactory.make()
        val newItem = oldItem.copy(id = "DIFFERENT_ID")
        assertFalse(diffCallback.areItemsTheSame(oldItem, newItem))
    }

    @Test
    fun `WHEN areContentsTheSame THEN call ItemUiModel method`() {
        val oldItem: TestItemUiModel = mockk(relaxed = true)
        val newItem = TestItemUiModelFactory.make()
        diffCallback.areContentsTheSame(oldItem, newItem)

        verify { oldItem.areContentsTheSame(newItem) }
    }
}
