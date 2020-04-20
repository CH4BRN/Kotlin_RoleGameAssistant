// File DomainIdeal.kt
// @Author pierre.antoine - 23/03/2020 - No copyright.

package com.uldskull.rolegameassistant.models.character

/**
 *   Class "DomainIdeal" :
 *   TODO: Fill class use.
 **/

class DomainIdeal(
    val idealId: Long?,
    val idealName: String?,
    val idealGoodPoints: Int?,
    val idealEvilPoints: Int?,
    var isChecked: Boolean
) {
    override fun toString(): String {
        return "id : $idealId\n" +
                "Name : $idealName\n" +
                "Good : ${idealGoodPoints.toString()}\n" +
                "Evil : ${idealEvilPoints.toString()}\n" +
                "Checked : $isChecked \n"
    }
}
