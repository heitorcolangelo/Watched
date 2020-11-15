package com.heitorcolangelo.presentation.common.mapper

import com.heitorcolangelo.domain.common.model.RawDateDomainModel
import com.heitorcolangelo.presentation.common.model.FormattedDateUiModel
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class MonthAndYearDateDomainUiMapper @Inject constructor(
    private val zoneId: ZoneId,
    private val locale: Locale
) : DomainUiMapper<RawDateDomainModel, FormattedDateUiModel> {
    companion object {
        const val MONTH_AND_YEAR_PATTERN = "MMM yyyy"
    }

    override fun mapToUiModel(domainModel: RawDateDomainModel): FormattedDateUiModel {
        return with(domainModel) {
            val monthYearFormatter = DateTimeFormatter
                .ofPattern(MONTH_AND_YEAR_PATTERN, locale)
                .withZone(zoneId)
            val defaultFormatter = DateTimeFormatter.ofPattern(pattern, locale)
            val localDate = LocalDate.parse(rawDate, defaultFormatter)
            monthYearFormatter.format(localDate)
            FormattedDateUiModel(formattedDate = monthYearFormatter.format(localDate))
        }
    }
}
