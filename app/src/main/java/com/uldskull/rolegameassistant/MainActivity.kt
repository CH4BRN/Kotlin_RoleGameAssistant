package com.uldskull.rolegameassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity

class MainActivity : AppCompatActivity() {

    /** Activity Lifecycle  **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Start new character activity.
        var intent = Intent(this, com.uldskull.rolegameassistant.ui.new_character.NewCharacterActivity::class.java)
        startActivity(intent)
    }
}
