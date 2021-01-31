package com.watched.data.common.source

/**
 * Sometimes the first page between data sources can be different, for example:
 * - Local data source's first page starts with 0
 * - Remote data source's first page starts with 1
 *
 * Implement this interface if your data source is a paged data source.
 */
interface PagedDataSource {
    val firstPage: Int
    fun pageToRequest(page: Int): Int {
        return firstPage + page
    }
}
