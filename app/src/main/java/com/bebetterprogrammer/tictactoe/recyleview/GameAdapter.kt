package com.eye.newtictacrecylview

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.eye.newtictacrecylview.utils.GamePlayUtility
import com.eye.newtictacrecylview.utils.GetPosition
import com.eye.newtictacrecylview.databinding.GamePlayViewBinding
import com.eye.newtictacrecylview.databinding.NewPlayWithComputerViewBinding
import com.eye.newtictacrecylview.databinding.NewPlayWithFriendViewBinding
import com.eye.newtictacrecylview.databinding.RecyleviewLytBinding

class GameAdapter : RecyclerView.Adapter<GameViewHolder>() {
    var gametype = 1 // ! : computer , 2: Friend
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            RecyleviewLytBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        holder.binding.startview.playWithJarvis.setOnClickListener {
            gametype = 1

            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.VISIBLE
            holder.binding.friendgameview.root.visibility = View.GONE
        }

        holder.binding.startview.playWithFriend.setOnClickListener {
            gametype = 2

            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.VISIBLE
        }

        holder.binding.computergameview.btnback.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
        }

        holder.binding.friendgameview.btnback.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
        }

        holder.binding.computergameview.btnQuit.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE

        }

        holder.binding.friendgameview.btnQuit.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
        }

        holder.binding.computergameview.btnPlay.setOnClickListener {
            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.VISIBLE
        }

        holder.binding.friendgameview.btnPlay.setOnClickListener {
            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.VISIBLE
        }

        holder.binding.gameview.btnback.setOnClickListener {

            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.GONE

        }

        holder.binding.gameview.btnQuit.setOnClickListener {

            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.GONE

        }

    }

    override fun getItemCount(): Int {
        return 1
    }
}

class GameViewHolder(val binding: RecyleviewLytBinding) : RecyclerView.ViewHolder(binding.root) {

}

class PlayWithComputer(val binding: NewPlayWithComputerViewBinding) {

    fun init() {

        val versionName = BuildConfig.VERSION_NAME
        binding.appBottomLine.text = "Designed @ bebetterprogrammer.com | v$versionName"

        var flag: Int = 0
        var flag1: Int = 0
        var flag2: Int = 0

        binding.diffLow.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
        binding.weaponCircle.setImageResource(R.drawable.ic_circle_secondary)
        binding.circleMove.setImageResource(R.drawable.ic_circle_secondary)

        binding.diffLow.setOnClickListener(View.OnClickListener {
            binding.diffLow.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            binding.diffMedium.setBackgroundResource(R.drawable.layout_difficulty_button)
            binding.diffHigh.setBackgroundResource(R.drawable.layout_difficulty_button)
            flag = 0
        })
        binding.diffMedium.setOnClickListener(View.OnClickListener {
            binding.diffLow.setBackgroundResource(R.drawable.layout_difficulty_button)
            binding.diffMedium.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            binding.diffHigh.setBackgroundResource(R.drawable.layout_difficulty_button)
            flag = 1
        })
        binding.diffHigh.setOnClickListener(View.OnClickListener {
            binding.diffLow.setBackgroundResource(R.drawable.layout_difficulty_button)
            binding.diffMedium.setBackgroundResource(R.drawable.layout_difficulty_button)
            binding.diffHigh.setBackgroundResource(R.drawable.layout_difficulty_button_secondary)
            flag = 2
        })

        binding.weaponCircle.setOnClickListener(View.OnClickListener {
            binding.weaponCircle.setImageResource(R.drawable.ic_circle_secondary)
            binding.weaponCross.setImageResource(R.drawable.ic_cross_white)
            flag1 = 0
        })
        binding.weaponCross.setOnClickListener(View.OnClickListener {
            binding.weaponCircle.setImageResource(R.drawable.ic_circle_white)
            binding.weaponCross.setImageResource(R.drawable.ic_cross_secondary)
            flag1 = 1
        })
        binding.circleMove.setOnClickListener(View.OnClickListener {
            binding.circleMove.setImageResource(R.drawable.ic_circle_secondary)
            binding.crossMove.setImageResource(R.drawable.ic_cross_white)
            flag2 = 0
        })
        binding.crossMove.setOnClickListener(View.OnClickListener {
            binding.circleMove.setImageResource(R.drawable.ic_circle_white)
            binding.crossMove.setImageResource(R.drawable.ic_cross_secondary)
            flag2 = 1
        })

        binding.btnPlay.setOnClickListener(View.OnClickListener {
            moveFirst(flag, flag1, flag2)
        })

    }

    private fun moveFirst(flag: Int, flag1: Int, flag2: Int) {



//        val intent = Intent(this, GamePlayActivity::class.java)
//        intent.putExtra("Flag", flag) // flag for difficulty level
//        intent.putExtra("Flag1", flag1) // Player Weapon
//        intent.putExtra("Flag2", flag2) // Who will move first
//        intent.putExtra("vsWhom", 1) // Vs Jarvis
//        startActivity(intent)
//        finish()
    }

}

class PlayWithFriend(val binding: NewPlayWithFriendViewBinding,val context: Context) {

    fun init() {

        val versionName = BuildConfig.VERSION_NAME
        binding.appBottomLine.text = "Designed @ bebetterprogrammer.com | v$versionName"

        var flag: Int = 0

            binding.circle.setImageResource(R.drawable.ic_circle_secondary)

        binding.circle.setOnClickListener(View.OnClickListener {
            binding.cross.setImageResource(R.drawable.ic_cross_white)
            binding.circle.setImageResource(R.drawable.ic_circle_secondary)
            flag = 0
        })

        binding.cross.setOnClickListener(View.OnClickListener {
            binding.circle.setImageResource(R.drawable.ic_circle_white)
            binding.cross.setImageResource(R.drawable.ic_cross_secondary)
            flag = 1
        })

        binding.btnPlay.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(binding.playerOne.text) || TextUtils.isEmpty(binding.playerTwo.text)) {
                if (TextUtils.isEmpty(binding.playerOne.text) && TextUtils.isEmpty(binding.playerTwo.text)) {
                    Toast.makeText(context, "Enter Player Name", Toast.LENGTH_SHORT).show()
                } else if (TextUtils.isEmpty(binding.playerOne.text)) {
                    Toast.makeText(context, "Enter Player_1 Name", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Enter Player_2 Name", Toast.LENGTH_SHORT).show()
                }
            } else {
//                val intent = Intent(this, GamePlayActivity::class.java)
//                intent.putExtra("Player1", player_one.text.toString())
//                intent.putExtra("Player2", player_two.text.toString())
//                intent.putExtra("vsWhom", 0) // Vs Friend
//                if (flag == 0) {
//                    intent.putExtra("Player", 0) // O will move first
//                } else if (flag == 1) {
//                    intent.putExtra("Player", 1) // X will move first
//                }
//                startActivity(intent)
//                finish()
            }
        })


    }

}

class GamePlay(val binding: GamePlayViewBinding) {
    var x: Int = 0
    var turn: Int = 0
    var first: Int = 0
    var pl: Int = 0
    var fl: Int = 0
    var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    lateinit var p1: String
    lateinit var p2: String
    var player : Int = 0
    var vsWhom : Int = 0
    var weapon : Int = 0
    var jarvis : Int = 0
    var whichFirst : Int = 0
    var whichLevel : Int = 0
    var done = 0
    var getP = GetPosition()
    var flag = false
    var played = 0
    var mFlag = false



    // 0 = O      1 = X     2 = blank
    private val obj = GamePlayUtility()
    var list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8).toMutableList()
    var isclicked = 0

    fun init() {

    }

}