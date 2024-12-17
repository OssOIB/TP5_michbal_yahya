package com.mastercyber.tp5.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    val regular: String? = null,
    val shiny: String? = null,
    val gmax: String? = null,
)
