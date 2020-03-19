// File UseCase.kt
// @Author pierre.antoine - 18/03/2020 - No copyright.

package com.uldskull.rolegameassistant.useCases

/**
 *   Interface "UseCase" :
 *   TODO: Fill interface use.
 **/
interface UseCase<in Request, out Response> {
   fun execute(request: Request): Response
}