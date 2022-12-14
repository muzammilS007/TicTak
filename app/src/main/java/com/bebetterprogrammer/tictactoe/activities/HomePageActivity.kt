package com.bebetterprogrammer.tictactoe.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bebetterprogrammer.tictactoe.BuildConfig
import com.bebetterprogrammer.tictactoe.R
import com.bebetterprogrammer.tictactoe.databinding.ActivityHomePageBinding
import com.bebetterprogrammer.tictactoe.recyleview.GameAdapter
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : BaseActivity() {
    var binding : ActivityHomePageBinding? = null
    var adapter : GameAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        adapter = GameAdapter()
        binding?.gamelistview?.layoutManager = LinearLayoutManager(this)
        binding?.gamelistview?.adapter = adapter

/*        val versionName = BuildConfig.VERSION_NAME
        appBottomLine.text = "Designed @ bebetterprogrammer.com | v$versionName"

        var musicPref: SharedPreferences = this.getSharedPreferences("Music", Context.MODE_PRIVATE)
        var pref = musicPref.getInt("Pref", 1)
        var editor: SharedPreferences.Editor = musicPref.edit()

        if (pref == 0) {
            musicOnbtn.isVisible = true
            musicOffbtn.isVisible = false
            startService(Intent(this, MusicService::class.java))
        } else {
            musicOnbtn.isVisible = false
            musicOffbtn.isVisible = true
            stopService(Intent(this, MusicService::class.java))
        }

        playWithJarvis.setOnClickListener {
            val intent = Intent(this, PlayWithJarvisActivity::class.java)
            startActivity(intent)
        }
        playWithFriend.setOnClickListener {
            val intent = Intent(this, PlayWithFriendActivity::class.java)
            startActivity(intent)
        }
        shareBtn.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=com.bebetterprogrammer.tictactoe"
            )
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
        ratingBtn.setOnClickListener {
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.bebetterprogrammer.tictactoe")
            val rateIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(rateIntent)
        }
        musicOffbtn.setOnClickListener {
            musicOnbtn.isVisible = true
            musicOffbtn.isVisible = false
            editor.putInt("Pref", 0)
            editor.commit()
            startService(Intent(this, MusicService::class.java))
        }
        musicOnbtn.setOnClickListener {
            musicOnbtn.isVisible = false
            musicOffbtn.isVisible = true
            editor.putInt("Pref", 1)
            editor.commit()
            stopService(Intent(this, MusicService::class.java))
        }*/
    }
}
