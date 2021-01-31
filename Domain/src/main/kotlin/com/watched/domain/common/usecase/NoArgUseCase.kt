package com.watched.domain.common.usecase

import com.watched.domain.common.model.DomainModel

abstract class NoArgUseCase<Model : DomainModel> {
    suspend fun execute(): Model {
        return invoke()
    }

    abstract suspend operator fun invoke(): Model
}
