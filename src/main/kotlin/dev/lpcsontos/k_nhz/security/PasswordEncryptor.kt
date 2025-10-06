package dev.lpcsontos.k_nhz.security

import org.mindrot.jbcrypt.BCrypt
import java.security.MessageDigest

fun sha256Hex(s: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(s.toByteArray(Charsets.UTF_8))
        .joinToString("") { "%02x".format(it) }
}

fun hash(password: String, rounds: Int = 10): String {
    val prehash = sha256Hex(password)
    return BCrypt.hashpw(prehash, BCrypt.gensalt(rounds))
}

fun verify(password: String, hash: String): Boolean {
    val prehash = sha256Hex(password)
    return BCrypt.checkpw(prehash, hash)
}