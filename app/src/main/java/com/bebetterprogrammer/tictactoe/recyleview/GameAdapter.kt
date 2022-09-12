package com.bebetterprogrammer.tictactoe.recyleview

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bebetterprogrammer.tictactoe.BuildConfig
import com.bebetterprogrammer.tictactoe.R
import com.bebetterprogrammer.tictactoe.databinding.*
import com.bebetterprogrammer.tictactoe.utils.GamePlayUtility
import com.bebetterprogrammer.tictactoe.utils.GetPosition
import com.bebetterprogrammer.tictactoe.utils.Result


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

            holder.startComputerGame()
        }

        holder.binding.startview.playWithFriend.setOnClickListener {
            gametype = 2

            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.VISIBLE

            holder.startGameWithFriend()
        }

        holder.binding.computergameview.btnback.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.gamereset()
        }

        holder.binding.friendgameview.btnback.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.gamereset()
        }

        holder.binding.computergameview.btnQuit.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE

            holder.gamereset()

        }

        holder.binding.friendgameview.btnQuit.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE

            holder.gamereset()
        }

        holder.binding.computergameview.btnPlay.setOnClickListener {
            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.VISIBLE

            holder.playcomputergame()
        }

        holder.binding.friendgameview.btnPlay.setOnClickListener {
            holder.binding.startview.root.visibility = View.GONE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.VISIBLE

            holder.playfriendgame()
        }

        holder.binding.gameview.btnback.setOnClickListener {

            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.GONE
            holder.gamereset()

        }

        holder.binding.gameview.btnQuit.setOnClickListener {
            holder.binding.startview.root.visibility = View.VISIBLE
            holder.binding.computergameview.root.visibility = View.GONE
            holder.binding.friendgameview.root.visibility = View.GONE
            holder.binding.gameview.root.visibility = View.GONE
            holder.gamereset()
        }


    }

    override fun getItemCount(): Int {
        return 1
    }
}

class GameViewHolder(val binding: RecyleviewLytBinding) : RecyclerView.ViewHolder(binding.root) {
    var playWithComputer = PlayWithComputer(binding.computergameview, binding.root.context)
    var playwithFriend = PlayWithFriend(binding = binding.friendgameview, context = binding.root.context)
    var playgame = GamePlay(context = binding.root.context, binding = binding.gameview)
    fun startComputerGame() {
        playWithComputer.init()
    }

    fun startGameWithFriend() {
        playwithFriend.init()
    }

    fun playcomputergame() {
        playWithComputer.moveFirst { flag, flag1, flag2 ->
            playgame.fromComputerGame(flag, flag1, flag2)
        }
    }

    fun playfriendgame() {
        playwithFriend.moveFirst { player1, player2, player ->
            playgame.fromFriendGame(player1, player2, player)
        }
    }

    fun gamereset(){
        playgame.reset()
      //  playgame = GamePlay(context = binding.root.context, binding = binding.gameview)
        binding.gameview.p1Winning.text = "0"
        binding.gameview.p2Winning.text = "0"

        binding.friendgameview.playerOne.text.clear()
        binding.friendgameview.playerTwo.text.clear()

    }

}

class PlayWithComputer(val binding: NewPlayWithComputerViewBinding, val context: Context) {
    var flag: Int = 0
    var flag1: Int = 0
    var flag2: Int = 0
    fun init() {

        val versionName = BuildConfig.VERSION_NAME
        binding.appBottomLine.text = "Designed @ bebetterprogrammer.com | v$versionName"



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

    }

    fun moveFirst(callbackflag: (flag: Int, flag1: Int, flag2: Int) -> Unit) {

        callbackflag(flag, flag1, flag2)

    }

}

class PlayWithFriend(val binding: NewPlayWithFriendViewBinding, val context: Context) {
    var flag: Int = 0
    var player1: String = ""
    var player2: String = ""
    var player: Int = 0
    fun init() {

        val versionName = BuildConfig.VERSION_NAME
        binding.appBottomLine.text = "Designed @ bebetterprogrammer.com | v$versionName"



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



/*

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
                player1 = binding.playerOne.text.toString()
                player2 = binding.playerTwo.text.toString()

                if (flag == 0) {
                    player = 0 // O will move first
                } else if (flag == 1) {
                    player = 1 // X will move first
                }
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
*/


    }

    fun moveFirst(callbackflag: (player1: String, player2: String, player: Int) -> Unit) {
//        player1 = binding.playerOne.text.toString()
//        player2 = binding.playerTwo.text.toString()
//
//        if (flag == 0) {
//            player = 0 // O will move first
//        } else if (flag == 1) {
//            player = 1 // X will move first
//        }

        if (TextUtils.isEmpty(binding.playerOne.text) || TextUtils.isEmpty(binding.playerTwo.text)) {
            if (TextUtils.isEmpty(binding.playerOne.text) && TextUtils.isEmpty(binding.playerTwo.text)) {
                Toast.makeText(context, "Enter Player Name", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.isEmpty(binding.playerOne.text)) {
                Toast.makeText(context, "Enter Player_1 Name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Enter Player_2 Name", Toast.LENGTH_SHORT).show()
            }
            return
        } else {
            player1 = binding.playerOne.text.toString()
            player2 = binding.playerTwo.text.toString()

            if (flag == 0) {
                player = 0 // O will move first
            } else if (flag == 1) {
                player = 1 // X will move first
            }
            callbackflag(player1, player2, player)
        }



    }

}

class GamePlay(val context: Context, val binding: GamePlayViewBinding?) {
    var x: Int = 0
    var turn: Int = 0
    var first: Int = 0
    var pl: Int = 0
    var fl: Int = 0
    var gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    lateinit var p1: String
    lateinit var p2: String
    var player: Int = 0
    var vsWhom: Int = 0
    var weapon: Int = 0
    var jarvis: Int = 0
    var whichFirst: Int = 0
    var whichLevel: Int = 0
    var done = 0
    var getP = GetPosition()
    var flag = false
    var played = 0
    var mFlag = false

    // 0 = O      1 = X     2 = blank
    val obj = GamePlayUtility()
    var list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8).toMutableList()
    var isclicked = 0

    fun fromComputerGame(flag0: Int = 0, flag1: Int = 0, flag2: Int = 0, vsWhom1: Int = 0) {

        p1 = "Player1"
        p2 = "Player2"
        player = 0

        weapon = flag0
        whichFirst = flag2
        whichLevel = flag1
        vsWhom = 1

        init()
    }


    fun fromFriendGame(player1: String, player2: String, Player: Int) {
        p1 = player1 ?: "Player1"
        p2 = player2 ?: "Player2"
        player = Player

        weapon = 0
        whichFirst = 0
        whichLevel = 0
        vsWhom = 0

        init()
    }


    fun init() {
        val versionName = BuildConfig.VERSION_NAME
        binding?.appBottomLine?.text = "Designed @ bebetterprogrammer.com | v$versionName"

        if (vsWhom == 0) { // Vs Friend
            binding?.Player1?.text = p1 // First Player Name
            binding?.Player2?.text = p2 // Second Player Name
            if (player == 0) {
                binding?.tvTurn?.text = "$p1's Turn"
                turn = 0
                pl = 1
            } else if (player == 1) {
                binding?.tvTurn?.text = "$p2's Turn"
                turn = 1
                pl = 0
            }
        } else if (vsWhom == 1) { // Vs Jarvis
            played++
            binding?.Player1?.text = "YOU"
            binding?.Player2?.text = "JARVIS"
            if (whichLevel == 0 || whichLevel == 1 || whichLevel == 2) {
                if (whichFirst == 0) {
                    first = 0 // O
                    fl = 0
                } else if (whichFirst == 1) {
                    first = 1 // X
                    fl = 1
                }
                if ((whichFirst == 0 && weapon == 0) || (whichFirst == 1 && weapon == 1)) {
                    binding?.tvTurn?.text = "Your Turn"
                } else {
                    binding?.tvTurn?.text = "Jarvis's Turn"
                }
                if (weapon == 0) {
                    turn = 0 // your O
                    pl = 0
                    jarvis = 1
                    done = 1
                } else if (weapon == 1) {
                    turn = 1 // your X
                    pl = 1
                    jarvis = 0
                    done = 1
                }
                if (turn != first) {
                    played = 1
                    done = 0
                    turn = if (turn == 0) {
                        1
                    } else {
                        0
                    }
                    putNew(getRandom())
                } else {
                    played = 0
                }
            }
        }

        binding?.btn1?.setOnClickListener {
            playerClick(it)
        }
        binding?.btn2?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn3?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn4?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn5?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn6?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn7?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn8?.setOnClickListener {
            playerClick(it)

        }
        binding?.btn9?.setOnClickListener {
            playerClick(it)

        }

    }

    fun playerClick(view: View) {
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()
        if (vsWhom == 0) {
            if (gameState[tappedImage] == 2 && obj.result != Result.TIE && obj.result != Result.WON) {
                gameState[tappedImage] = turn
                if (turn == 0) {
                    binding?.tvTurn?.text = "$p2's Turn"
                    img.setImageResource(R.drawable.ic_circle_secondary)
                    val animFadeIn =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    img.startAnimation(animFadeIn)
                } else if (turn == 1) {
                    binding?.tvTurn?.text = "$p1's Turn"
                    img.setImageResource(R.drawable.ic_cross_yellow)
                    val animFadeIn =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    img.startAnimation(animFadeIn)
                }
                obj.isWin(
                    gameState,
                    vsWhom,
                    turn,
                    binding?.p1Winning!!,
                    binding.p2Winning,
                    -1,
                    binding.player1Trophy,
                    binding.player2Trophy
                )
                if (obj.result == Result.WON && turn == 1) {
                    openDialogBox(view, p2)
                } else if (obj.result == Result.WON && turn == 0) {
                    openDialogBox(view, p1)
                } else if (obj.result == Result.TIE) {
                    openDialogBox(view, "That was a Tie!")
                }
                if (obj.result != Result.TIE && obj.result != Result.WON) {
                    turn++
                    turn %= 2
                }
            }
        } else if (vsWhom == 1 && done % 2 == 1) {
            if (gameState[tappedImage] == 2 && obj.result != Result.TIE && obj.result != Result.WON && obj.result != Result.LOST && turn == weapon) {
                gameState[tappedImage] = turn
                list.remove(tappedImage)
                if (turn == weapon) {
                    binding?.tvTurn?.text = "Jarvis's Turn"
                } else {
                    binding?.tvTurn?.text = "Your Turn"
                }
                if (turn == 0) {
                    img.setImageResource(R.drawable.ic_circle_secondary)
                    val animFadeIn =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    img.startAnimation(animFadeIn)
                } else if (turn == 1) {
                    img.setImageResource(R.drawable.ic_cross_yellow)
                    val animFadeIn =
                        AnimationUtils.loadAnimation(context, R.anim.fade_in)
                    img.startAnimation(animFadeIn)
                }
                done++
                obj.isWin(
                    gameState,
                    vsWhom,
                    turn,
                    binding?.p1Winning!!,
                    binding.p2Winning,
                    weapon,
                    binding.player1Trophy,
                    binding.player2Trophy
                )
                if (obj.result != Result.TIE && obj.result != Result.WON) {
                    turn++
                    turn %= 2
                    putNew(getRandom())
                }
                if (obj.result == Result.WON) {
                    openDialogBox(view, "YOU")
                } else if (obj.result == Result.TIE) {
                    if (!flag) {
                        openDialogBox(view, "TIE")
                    }
                }
            }
        }
    }

    private fun getRandom(): ImageView {
        val r = getP.getPos(list, whichLevel, gameState, jarvis, weapon)
        isclicked = r
        val q = listOf<ImageView>(
            binding?.btn1!!,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )
        for (i: ImageView in q) {
            if (gameState[isclicked] == 2) {
                return q[isclicked]
            }
        }
        return getRandom()
    }

    private fun putNew(o: ImageView) {
        if (turn == 0) {
            Handler().postDelayed({
                binding?.tvTurn?.text = "Your Turn"
                o.setImageResource(R.drawable.ic_circle_secondary)
                val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                o.startAnimation(animFadeIn)
                done++
            }, 400)
        } else if (turn == 1) {
            Handler().postDelayed({
                binding?.tvTurn?.text = "Your Turn"
                o.setImageResource(R.drawable.ic_cross_yellow)
                val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                o.startAnimation(animFadeIn)
                done++
            }, 400)
        }
        gameState[isclicked] = turn
        list.remove(isclicked)
        obj.isWin(
            gameState,
            vsWhom,
            turn,
            binding?.p1Winning!!,
            binding.p2Winning,
            weapon,
            binding.player1Trophy,
            binding.player2Trophy
        )
        if (obj.result == Result.LOST) {
            openDialogBox(o, "JARVIS")
        } else if (obj.result == Result.TIE) {
            flag = true
            openDialogBox(o, "TIE")
        }
        if (obj.result != Result.TIE && obj.result != Result.WON) {
            turn++
            turn %= 2
        }
    }

    private fun openDialogBox(v: View, playerName: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
        val alertDialogbinding =
            ResultDialogBinding.inflate(LayoutInflater.from(v.context), null, false)
        builder.setView(alertDialogbinding.root)
        val alertDialog: AlertDialog = builder.create()
        if (vsWhom == 0) {
            if (obj.result == Result.WON) {
                mFlag = true
                alertDialogbinding.resultTrophy.setImageResource(R.drawable.ic_trophy_won)
                alertDialogbinding.result.text = "Yeppii.. $playerName Won!"
                alertDialogbinding.animationView.visibility = View.VISIBLE
            } else if (obj.result == Result.TIE) {
                mFlag = true
                alertDialogbinding.resultTrophy.setImageResource(R.drawable.ic_trophy_tie)
                alertDialogbinding.result.text = playerName
                alertDialogbinding.animationView.visibility = View.VISIBLE
            }
        } else if (vsWhom == 1) {
            if (obj.result != Result.TIE) {
                if (obj.playerWon) {
                    mFlag = true
                    alertDialogbinding.resultTrophy.setImageResource(R.drawable.ic_trophy_won)
                    alertDialogbinding.result.text = "Yeppii.. You Won!"
                    alertDialogbinding.animationView.visibility = View.VISIBLE
                } else if (obj.result == Result.LOST) {
                    mFlag = true
                    alertDialogbinding.resultTrophy.setImageResource(R.drawable.ic_trophy_lost)
                    alertDialogbinding.result.text = "Ohh... You Lost!"
                }
            } else {
                mFlag = true
                alertDialogbinding.resultTrophy.setImageResource(R.drawable.ic_trophy_tie)
                alertDialogbinding.result.text = "That was a tie!"
                alertDialogbinding.animationView.visibility = View.VISIBLE
            }
        }
        alertDialog.setCancelable(false)

        val animFadeIn = AnimationUtils.loadAnimation(context, R.anim.result_fade_in)
        Handler().postDelayed({
            alertDialogbinding.animationView.startAnimation(animFadeIn)
            alertDialog.show()
        }, 500)

        alertDialogbinding.btnRematch.setOnClickListener {
            val animFadeOut =
                AnimationUtils.loadAnimation(context, R.anim.result_fade_out)
            alertDialogbinding.animationView.startAnimation(animFadeOut)
            Handler().postDelayed({
                reset()
                alertDialog.dismiss()
            }, 500)
        }

        alertDialogbinding.btnQuit.setOnClickListener {
            val animFadeOut =
                AnimationUtils.loadAnimation(context, R.anim.result_fade_out)
            alertDialogbinding.animationView.startAnimation(animFadeOut)
            Handler().postDelayed({
                reset()
                alertDialog.dismiss()

            }, 400)
        }
    }


     fun reset() {
        flag = false
        for (i in 0..8) {
            gameState[i] = 2
        }
        var q = listOf<ImageView>(
            binding?.btn1!!,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )
        for (i: ImageView in q) {
            i.setImageResource(0)
        }
        player = pl
        if (x == 0) {
            turn = pl
            first = fl
        }
        obj.playerWon = false
        list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8).toMutableList()
        obj.result = null
        if (vsWhom == 1) {
            if (turn != first && played == 1) {
                first = turn
                x = 1
            }
            if (turn == first && played == 0) {
                x = 0
                first++
                first %= 2
                played = 1
            }
            if (first == turn) {
                played = 0
            }
            if (turn != first) {
                done = 0
                turn = if (turn == 0) {
                    1
                } else {
                    0
                }
                putNew(getRandom())
            } else {
                done = 1
            }
            if (turn == weapon) {
                binding.tvTurn.text = "Your Turn"
            } else {
                binding.tvTurn.text = "Jarvis's Turn"
            }
        } else if (vsWhom == 0) {
            if (pl == 0) {
                pl = 1
            } else {
                pl = 0
            }
            if (player == 0) {

                binding.tvTurn.text = "$p1's Turn"
            } else if (player == 1) {
                binding.tvTurn.text = "$p2's Turn"
            }
        }
    }


}