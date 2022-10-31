class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		// reset button listener
		button10.setOnClickListener {
			reset()

		}
	}
	
	// player winning count
	var player1Count = 0
	var player2Count = 0

	// this function handle the click event on the board.
	fun clickfun(view:View)
	{
		if(playerTurn) {
			val but = view as Button
			var cellID = 0
			when (but.id) {
				R.id.button -> cellID = 1
				R.id.button2 -> cellID = 2
				R.id.button3 -> cellID = 3
				R.id.button4 -> cellID = 4
				R.id.button5 -> cellID = 5
				R.id.button6 -> cellID = 6
				R.id.button7 -> cellID = 7
				R.id.button8 -> cellID = 8
				R.id.button9 -> cellID = 9
			}
			playerTurn = false;
			Handler().postDelayed(Runnable { playerTurn = true } , 600)
			playnow(but, cellID)
		}
	}
	var player1 = ArrayList<Int>()
	var player2 = ArrayList<Int>()
	var emptyCells = ArrayList<Int>()
	var activeUser = 1

	// this function update update the game board after every move.
	fun playnow(buttonSelected:Button , currCell:Int)
	{ val audio = MediaPlayer.create(this , R.raw.poutch)
		if(activeUser == 1)
		{
			buttonSelected.text = "X"
			buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
			player1.add(currCell)
			emptyCells.add(currCell)
			audio.start()
			buttonSelected.isEnabled = false
			Handler().postDelayed(Runnable { audio.release() } , 200)
			val checkWinner = checkwinner()
			if(checkWinner == 1){
				Handler().postDelayed(Runnable { reset() } , 2000)
			}
			else if(singleUser){
				Handler().postDelayed(Runnable { robot() } , 500)
			}
			else
				activeUser = 2
		}
		else
		{
			buttonSelected.text = "O"
			audio.start()
			buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
			activeUser = 1
			player2.add(currCell)
			emptyCells.add(currCell)
			Handler().postDelayed(Runnable { audio.release() } , 200)
			buttonSelected.isEnabled = false
			val checkWinner = checkwinner()
			if(checkWinner == 1)
				Handler().postDelayed(Runnable { reset() } , 4000)
		}
	}
	
	// this function resets the game.
	fun reset()
	{
		player1.clear()
		player2.clear()
		emptyCells.clear()
		activeUser = 1;
		for(i in 1..9)
		{
			var buttonselected : Button?
			buttonselected = when(i){
				1 -> button
				2 -> button2
				3 -> button3
				4 -> button4
				5 -> button5
				6 -> button6
				7 -> button7
				8 -> button8
				9 -> button9
				else -> {button}
			}
			buttonselected.isEnabled = true
			buttonselected.text = ""
			textView.text = "Player1 : $player1Count"
			textView2.text = "Player2 : $player2Count"
		}
	}
	
	// this function disable all the button on the board for a while.
	fun disableReset()
	{
		button10.isEnabled = false
		Handler().postDelayed(Runnable { button10.isEnabled = true } , 2200)
	}
}
