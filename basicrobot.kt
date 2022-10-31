// This function automatically make a move at a random position.
fun robot()
	{
		val rnd = (1..9).random()
		if(emptyCells.contains(rnd))
			robot()
		else {
				val buttonselected : Button?
				buttonselected = when(rnd) {
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
			emptyCells.add(rnd);
			// move audio
			val audio = MediaPlayer.create(this , R.raw.poutch)
			audio.start()
			Handler().postDelayed(Runnable { audio.release() } , 500)
			buttonselected.text = "O"
			buttonselected.setTextColor(Color.parseColor("#D22BB804"))
			player2.add(rnd)
			buttonselected.isEnabled = false
			var checkWinner = checkwinner()
			if(checkWinner == 1)
				Handler().postDelayed(Runnable { reset() } , 2000)
	}
}
