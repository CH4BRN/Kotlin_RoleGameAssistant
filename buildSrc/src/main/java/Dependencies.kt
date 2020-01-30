/** File Dependencies.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

object AndroidVersions {
    const val comAndroidSupportAppcompatV7 = "26.1.0"
}

object AndroidXVersions {
    const val appcompatAppcompat = "1.1.0"
    const val coreCoreKtx = "1.1.0"
    const val constraintLayoutConstraintLayout = "1.1.3"
}

object JunitVersions {
    const val junitJunit = "4.12"
    const val androidxTestExtJunit = "1.1.1"
}

object EspressoVersions {
    const val androidxTestEspressoEspressoCore = "3.2.0"
}

object KoinVersion {
    const val koinAndroid = "2.0.1"
}

object RoomVersions {
    const val room = "2.2.1"
}

object RoomDependencies {
    const val androidxRoomRoomRuntime = "androidx.room:room-runtime:${RoomVersions.room}"
    const val androidxRoomRoomKtx = "androidx.room:room-ktx:${RoomVersions.room}"
    const val androidxRoomRoomCompiler = "androidx.room:room-compiler:${RoomVersions.room}"

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

object AndroidXDependencies {
    const val androidxAppcompatAppcompat =
        "androidx.appcompat:appcompat:" +
                AndroidXVersions.appcompatAppcompat
    const val androidxCoreCoreKtx = "androidx.core:core-ktx:" +
            AndroidXVersions.coreCoreKtx
    const val androidxConstraintLayoutConstraintLayout =
        "androidx.constraintlayout:constraintlayout:" +
                AndroidXVersions.constraintLayoutConstraintLayout
}

object TestDependencies {
    const val junitJunit = "junit:junit:${JunitVersions.junitJunit}"
    const val androidxTestExtJunit = "androidx.test.ext:junit:${JunitVersions.androidxTestExtJunit}"
    const val androidxTestEspressoEspressoCore =
        "androidx.test.espresso:espresso-core:${EspressoVersions.androidxTestEspressoEspressoCore}"
    const val androidxRoomRoomTesting = "androidx.room:room-testing:${RoomVersions.room}"
}