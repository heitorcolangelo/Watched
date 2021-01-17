package com.watched.domain.common.usecase

import com.watched.domain.common.model.DomainModel

abstract class NoArgUseCase<Model : DomainModel> {
    abstract suspend operator fun invoke(): Model
}
