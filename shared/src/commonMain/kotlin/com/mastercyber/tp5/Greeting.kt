package com.mastercyber.tp5

import com.mastercyber.tp5.dataclass.Pokemon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchPokemonWithImage(): Pair<String?, String?> {
        val random = (1..1025).random()
        return try {
            val response: Pokemon = client.get("https://tyradex.vercel.app/api/v1/pokemon/$random").body()
            val pokemonNameFr = response.name?.fr
            val imageUrl = response.sprites?.regular
            pokemonNameFr to imageUrl
        } catch (e: Exception) {
            "Unknown" to null
        }
    }

    suspend fun fetchPokemonImage(url: String?): ByteArray? {
        return try {
            url?.let { client.get(it).body() }
        } catch (e: Exception) {
            null
        }
    }
}

