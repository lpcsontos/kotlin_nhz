package dev.lpcsontos.k_nhz.security

import dev.lpcsontos.k_nhz.service.UserService
import dev.lpcsontos.k_nhz.utils.random50


suspend fun generateUniqueSlug(userService: UserService): String {
    var slug: String
    var isUnique: Boolean

    do {
        slug = random50()
        isUnique = userService.findUserBySlug(slug) == null
    } while (!isUnique)

    return slug
}