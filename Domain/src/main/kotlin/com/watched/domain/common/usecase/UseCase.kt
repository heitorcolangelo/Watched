package com.watched.domain.common.usecase

import com.watched.domain.common.model.DomainModel

abstract class UseCase<Model : DomainModel, Args : UseCaseArgs> {
    suspend fun execute(args: Args): Model {
        return invoke(args)
    }

    protected abstract suspend operator fun invoke(args: Args): Model
}
