package com.watched.domain.common.model

data class RawDateDomainModel(
    val rawDate: String,
    val pattern: String = DEFAULT_RAW_DATE_PATTERN
) : DomainModel() {
    companion object {
        const val DEFAULT_RAW_DATE_PATTERN = "yyyy-MM-dd"
    }
}
