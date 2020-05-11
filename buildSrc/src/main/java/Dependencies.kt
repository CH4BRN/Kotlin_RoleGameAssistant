import AndroidVersions.AppcompatV7
import AndroidXVersions.appcompat
import AndroidXVersions.constraintLayout
import AndroidXVersions.coreCoreKtx
import AndroidXVersions.legacySupport
import AndroidXVersions.lifecycle_version
import AndroidXVersions.viewPager2
import GoogleVersions.gson
import KoinVersion.koinAndroid
import KotlinxVersions.coroutines
import RoomVersions.room
import DokkaVersion.dokka
import MaterialVersions.Material

/** File Dependencies.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

object DokkaDependencies {
    const val Dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${dokka}"
}

object RoomDependencies {
    const val RoomRuntime = "androidx.room:room-runtime:${room}"
    const val RoomKtx = "androidx.room:room-ktx:${room}"
    const val RoomCompiler = "androidx.room:room-compiler:${room}"
}

object GoogleDependencies {
    const val Gson = "com.google.code.gson:gson:${gson}"
}

object ProjectsDependencies {
    const val di = ":infrastructure_di"
    const val presentation = ":presentation"
    const val domain = ":domain"
    const val data = ":infrastructure_data"
    const val app = ":app"
}

object KoinDependencies {
    const val KoinAndroid =
        "org.koin:koin-android:${koinAndroid}"
    const val KoinCore =
        "org.koin:koin-core:${koinAndroid}"
    const val KoinAndroidxScope =
        "org.koin:koin-androidx-scope:${koinAndroid}"
    const val KoinAndroidxViewmodel =
        "org.koin:koin-androidx-viewmodel:${koinAndroid}"
    // Koin for Unit tests
    const val KoinTest = "org.koin:koin-test:${koinAndroid}"

}

object AndroidDependencies {
    const val appcompatV7 =
        "com.android.support:appcompat-v7:${AppcompatV7}"
}

object MaterialDependencies {
    const val material =
        "com.google.android.material:material:$Material"
}


object AndroidXDependencies {
    const val Appcompat =
        "androidx.appcompat:appcompat:${appcompat}"
    const val CoreKtx = "androidx.core:core-ktx:${coreCoreKtx}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${constraintLayout}"
    const val Recyclerview = "androidx.recyclerview:recyclerview:${appcompat}"


    const val LifecycleLivedataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    const val LifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

    const val LegacySupportV4 = "androidx.legacy:legacy-support-v4:${legacySupport}"

    const val ViewPager2 = "androidx.viewpager2:viewpager2:${viewPager2}"

}

object KotlinXDependencies {
    const val kotlinxCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}"
    const val kotlinxCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}"
}

object TestDependencies {
    const val junitJunit = "junit:junit:${JunitVersions.junitJunit}"
    const val mockitoCore = "org.mockito:mockito-core:${MockitoVersions.mockito}"
    const val Junit = "androidx.test.ext:junit:${JunitVersions.ExtJunit}"
    const val EspressoCore =
        "androidx.test.espresso:espresso-core:${EspressoVersions.EspressoCore}"
    const val RoomTesting = "androidx.room:room-testing:$room"
    const val AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}

