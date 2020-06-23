import AndroidVersions.AppcompatV7
import AndroidXVersions.appcompat
import AndroidXVersions.constraintLayout
import AndroidXVersions.coreCoreKtx
import AndroidXVersions.legacySupport
import AndroidXVersions.lifecycle_version
import AndroidXVersions.viewPager2
import GoogleVersions.gson
import KoinVersions.koinAndroid
import KotlinxVersions.coroutines
import RoomVersions.room
import MaterialVersions.Material

/** File Dependencies.kt
 *   @Author pierre.antoine - 27/01/2020 - No copyright.
 **/

/**
 * Room dependencies
 */
object RoomDependencies {
    /**
     * Room runtime
     */
    const val RoomRuntime = "androidx.room:room-runtime:${room}"

    /**
     * Room ktx
     */
    const val RoomKtx = "androidx.room:room-ktx:${room}"

    /**
     * Room compiler
     */
    const val RoomCompiler = "androidx.room:room-compiler:${room}"
}

/**
 * Google dependencies
 */
object GoogleDependencies {
    /**
     * Google GSon
     */
    const val Gson = "com.google.code.gson:gson:${gson}"
}

/**
 * Project dependencies
 */
object ProjectsDependencies {
    /**
     * DI module
     */
    const val di = ":infrastructure_di"

    /**
     * Presentation module
     */
    const val presentation = ":presentation"

    /**
     * Domain module
     */
    const val domain = ":domain"

    /**
     * Data module
     */
    const val data = ":infrastructure_data"

    /**
     * App module
     */
    const val app = ":app"

    /**
     * Services module
     */
    const val services = ":services"
}

/**
 * Koin dependencies for DI
 */
object KoinDependencies {
    /**
     * Koin android
     */
    const val KoinAndroid =
        "org.koin:koin-android:${koinAndroid}"

    /**
     * Koin core
     */
    const val KoinCore =
        "org.koin:koin-core:${koinAndroid}"

    /**
     * Koin androidX scope
     */
    const val KoinAndroidxScope =
        "org.koin:koin-androidx-scope:${koinAndroid}"

    /**
     * Koin androidX ViewModel
     */
    const val KoinAndroidxViewmodel =
        "org.koin:koin-androidx-viewmodel:${koinAndroid}"

    /**
     * Koin test for unit testing
     */
    const val KoinTest = "org.koin:koin-test:${koinAndroid}"

}

/**
 * Android dependencies
 */
object AndroidDependencies {
    /**
     * App compat V7
     */
    const val appcompatV7 =
        "com.android.support:appcompat-v7:${AppcompatV7}"
}

/**
 * Material dependencies
 */
object MaterialDependencies {
    /**
     * Google material
     */
    const val material =
        "com.google.android.material:material:$Material"
}

/**
 * AndroidX dependencies
 */
object AndroidXDependencies {
    /**
     * App compat
     */
    const val Appcompat =
        "androidx.appcompat:appcompat:${appcompat}"

    /**
     * Core ktx
     */
    const val CoreKtx = "androidx.core:core-ktx:${coreCoreKtx}"

    /**
     * Constraint layout
     */
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${constraintLayout}"

    /**
     * Recycler view
     */
    const val Recyclerview = "androidx.recyclerview:recyclerview:${appcompat}"

    /**
     * lifecycle Live data
     */
    const val LifecycleLivedataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    /**
     * Lifecycle ViewModel
     */
    const val LifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

    /**
     * Legacy support
     */
    const val LegacySupportV4 = "androidx.legacy:legacy-support-v4:${legacySupport}"

    /**
     * Viewpager 2
     */
    const val ViewPager2 = "androidx.viewpager2:viewpager2:${viewPager2}"

}

/**
 * Kotlin X dependencies
 */
object KotlinXDependencies {
    /**
     * Coroutines core
     */
    const val kotlinxCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}"

    /**
     * Coroutines android
     */
    const val kotlinxCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}"
}

/**
 * Test dependencies
 */
object TestDependencies {
    /**
     * Junit
     */
    const val junitJunit = "junit:junit:${JunitVersions.junitJunit}"

    /**
     * Mockito core
     */
    const val mockitoCore = "org.mockito:mockito-core:${MockitoVersions.mockito}"

    /**
     * AndroidX Junit
     */
    const val androidXJunit = "androidx.test.ext:junit:${JunitVersions.ExtJunit}"

    /**
     * Espresso core
     */
    const val EspressoCore =
        "androidx.test.espresso:espresso-core:${EspressoVersions.EspressoCore}"

    /**
     * Room testing
     */
    const val RoomTesting = "androidx.room:room-testing:$room"

    /**
     * Android JUnit runner
     */
    const val AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
}

