// ActivityUtil.kt created by UldSkull - 25/02/2020

package com.uldskull.rolegameassistant.activities

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.uldskull.rolegameassistant.activities.Tag.TAG


inline fun FragmentManager.doTransaction(func: FragmentTransaction.() -> Unit) {
    Log.d(TAG, "doTransaction")
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
    Log.d(TAG, "replaceFragment")
    supportFragmentManager.doTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment) {
    Log.d(TAG, "addFragment")
    supportFragmentManager.doTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    Log.d(TAG, "addFragment")
    supportFragmentManager.doTransaction { remove(fragment) }
}

object Tag {
    const val TAG = "ActivityUtil"
}