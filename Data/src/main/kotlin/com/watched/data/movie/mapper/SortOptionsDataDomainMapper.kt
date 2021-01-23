package com.watched.data.movie.mapper

import com.watched.data.common.mapper.DataDomainMapper
import com.watched.data.movie.model.SortOptionsDataModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import javax.inject.Inject

class SortOptionsDataDomainMapper @Inject constructor() : DataDomainMapper<SortOptionsDataModel, SortOptionsDomainModel> {
    override fun mapToDomainModel(dataModel: SortOptionsDataModel): SortOptionsDomainModel {
        return when (dataModel) {
            SortOptionsDataModel.Popularity -> SortOptionsDomainModel.Popularity
            SortOptionsDataModel.TopRated -> SortOptionsDomainModel.TopRated
        }
    }

    override fun mapToDataModel(domainModel: SortOptionsDomainModel): SortOptionsDataModel {
        return when (domainModel) {
            SortOptionsDomainModel.Popularity -> SortOptionsDataModel.Popularity
            SortOptionsDomainModel.TopRated -> SortOptionsDataModel.TopRated
        }
    }
}
