// DamageBonus.kt created by UldSkull - 23/06/2020

package com.uldskull.rolegameassistant.useCases.damageBonus

/**
 * Enum "DamageBonus"
 *
 * Enum for damage bonus
 */
enum class DamageBonus(value: String) {
    Minus1D6("-1D6"),
    Minus1D4("-1D4"),
    None("nothing"),
    Plus1D4("+1D4"),
    Plus1D6("+1D6"),
    Plus2D6("+2D6"),
    Plus3D6("+3D6"),
    Plus4D6("+4D6"),
    Plus5D6("+5D6"),
    Plus6D6("+6D6"),
    Plus7D6("+7D6"),
    Plus8D6("+8D6"),
    Plus9D6("+9D6"),
    Plus10D6("+10D6")
}