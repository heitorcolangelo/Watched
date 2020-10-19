package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel

abstract class UseCase<Model : DomainModel, Args : UseCaseArgs> {
    suspend fun get(args: Args): Model {
        return invoke(args)
    }

    protected abstract suspend operator fun invoke(args: Args): Model
}
