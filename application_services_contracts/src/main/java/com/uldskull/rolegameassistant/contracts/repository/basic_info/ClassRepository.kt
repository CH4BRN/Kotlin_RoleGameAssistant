// ClassRepository.kt created by UldSkull - 12/02/2020

package com.uldskull.rolegameassistant.contracts.repository.basic_info

import GenericRepository
import com.uldskull.rolegameassistant.models.basic_info.DomainClass
<<<<<<< HEAD

=======
import com.uldskull.rolegameassistant.repository.GenericRepository
>>>>>>> e7ef618d5525040e5ea05d137966ac1ecc64df65

/**
Interface "ClassRepository"

The com.uldskull.rolegameassistant.com.uldskull.rolegameassistant.contracts.repository class will be responsible for interacting with
the Room database on behalf of the ViewModel and will need to provide methods that use
the DAO to insert, delete and query basic info records.
With the exception of the getAllBasicInfo() DAO method
(which returns a LiveData object)
these database operations will need to be performed on separate threads from the main
thread using the AsyncTask class.
 **/
interface ClassRepository<T> :
    GenericRepository<T, DomainClass>