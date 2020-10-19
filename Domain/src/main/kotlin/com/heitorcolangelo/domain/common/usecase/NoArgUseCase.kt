package com.heitorcolangelo.domain.common.usecase

import com.heitorcolangelo.domain.common.model.DomainModel

abstract class NoArgUseCase<Model : DomainModel> {
    abstract suspend operator fun invoke(): Model
}
