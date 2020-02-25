// BasicInfoRepository.kt created by UldSkull - 11/02/2020

<<<<<<< HEAD:domain/src/main/java/com/uldskull/rolegameassistant/models/basic_info/BasicInfoRepository.kt
package com.uldskull.rolegameassistant.models.basic_info

import GenericRepository


=======
package com.uldskull.rolegameassistant.repository.basic_info

import com.uldskull.rolegameassistant.base.GetAllRequest
import com.uldskull.rolegameassistant.base.GetCounterRequest
import com.uldskull.rolegameassistant.base.Response
import com.uldskull.rolegameassistant.models.basic_info.DomainBasicInfo
import com.uldskull.rolegameassistant.repository.GenericRepository

>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65:domain/src/main/java/com/uldskull/rolegameassistant/repository/basic_info/BasicInfoRepository.kt

/**
Interface "BasicInfoRepository"

The com.uldskull.rolegameassistant.com.uldskull.rolegameassistant.contracts.repository class will be responsible for interacting with
the Room database on behalf of the ViewModel and will need to provide methods that use
the DAO to insert, delete and query basic info records.
With the exception of the getAllBasicInfo() DAO method
(which returns a LiveData object)
these database operations will need to be performed on separate threads from the main
thread using the AsyncTask class.
 **/
<<<<<<< HEAD:domain/src/main/java/com/uldskull/rolegameassistant/models/basic_info/BasicInfoRepository.kt
interface BasicInfoRepository<T>:GenericRepository<T, DomainBasicInfo>{
    suspend fun getCounter(request: GetCounterRequest): Response<Int> {
        return Response.Success(100)
    }
=======
interface BasicInfoRepository<T> : GenericRepository<T, DomainBasicInfo> {
    suspend fun getCounter(request: GetCounterRequest): Response<Int?>

    suspend fun getAll(request: GetAllRequest): Response<T?>
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65:domain/src/main/java/com/uldskull/rolegameassistant/repository/basic_info/BasicInfoRepository.kt
}