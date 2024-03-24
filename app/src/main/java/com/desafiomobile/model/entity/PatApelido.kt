package com.desafiomobile.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PatApelido(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pat: Int,
    val apelido: String
)
