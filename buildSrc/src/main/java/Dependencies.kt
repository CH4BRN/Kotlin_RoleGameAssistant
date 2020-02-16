import AndroidXVersions.lifecycle_version

/** File Dependencies.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/


object RoomDependencies {
    const val androidxRoomRoomRuntime = "androidx.room:room-runtime:${RoomVersions.room}"
    const val androidxRoomRoomKtx = "androidx.room:room-ktx:${RoomVersions.room}"
    const val androidxRoomRoomCompiler = "androidx.room:room-compiler:${RoomVersions.room}"
}

object ProjectsDependencies {
    const val di = ":infrastructure_di"
    const val presentation = ":presentation"
    const val domain = ":domain"
    const val data = ":infrastructure_data"
    const val app = ":app"
    const val application_services = ":application_services"
    const val application_services_contracts = ":application_services_contracts"
    const val domain_services = ":domain_services"
}

object KoinDependencies {
    const val orgKoinKoinAndroid =
        "org.koin:koin-android:${KoinVersion.koinAndroid}"
    const val orgKoinKoinCore =
        "org.koin:koin-core:${KoinVersion.koinAndroid}"
    const val orgKoinKoinAndroidxScope =
        "org.koin:koin-androidx-scope:${KoinVersion.koinAndroid}"
    const val orgKoinKoinAndroidxViewmodel =
        "org.koin:koin-androidx-viewmodel:${KoinVersion.koinAndroid}"
}

object AndroidDependencies {
    const val androidSupportAppcompatV7 =
        "com.android.support:appcompat-v7:${AndroidVersions.comAndroidSupportAppcompatV7}"
}

object MaterialDependencies {
    const val comGoogleAndroidMaterialMaterial =
        "com.google.android.material:material:${MaterialVersions.comGoogleAndroidMaterialMaterial}"
}


object AndroidXDependencies {
    const val androidxAppcompatAppcompat =
        "androidx.appcompat:appcompat:" +
                AndroidXVersions.appcompatAppcompat
    const val androidxCoreCoreKtx = "androidx.core:core-ktx:" +
            AndroidXVersions.coreCoreKtx
    const val androidxConstraintLayoutConstraintLayout =
        "androidx.constraintlayout:constraintlayout:" +
                AndroidXVersions.constraintLayoutConstraintLayout
    const val androidxRecyclerviewRecyclerview = "androidx.recyclerview:recyclerview:" +
            AndroidXVersions.appcompatAppcompat
    const val androidxLifecycleLifecycleLivedataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val androidxLifecycleLifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
}

object TestDependencies {
    const val junitJunit = "junit:junit:${JunitVersions.junitJunit}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${JunitVersions.androidxTestExtJunit}"
    const val androidxTestEspressoEspressoCore =
        "androidx.test.espresso:espresso-core:${EspressoVersions.androidxTestEspressoEspressoCore}"
    const val androidxRoomRoomTesting = "androidx.room:room-testing:${RoomVersions.room}"
    const val androidTestRunnerAndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}