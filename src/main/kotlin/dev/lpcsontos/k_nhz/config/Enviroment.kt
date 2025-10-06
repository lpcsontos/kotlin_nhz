package dev.lpcsontos.k_nhz.config

import io.github.cdimascio.dotenv.dotenv

object Env {
    private val env = dotenv()

    operator fun get(key: String, default: String?): String? {
        return env[key] ?: default
    }

    operator fun get(key: String): String {
        return env[key] ?: error("Missing $key in .env")
    }
}