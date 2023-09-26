package com.example.rockets.data.local_database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.rockets.data.local_database.entities.payload_weights.PayloadWeightsEntity

data class RocketsAndHeightWithPayloadWeightEntity(
    @Embedded val rocketAndHeight: RocketAndHeight,
    @Relation(
        entity = PayloadWeightsEntity::class,
        parentColumn = "id",
        entityColumn = "rocketEntityId"
    )
    val payloadWeightsEntityList: List<PayloadWeightsEntity>
)
