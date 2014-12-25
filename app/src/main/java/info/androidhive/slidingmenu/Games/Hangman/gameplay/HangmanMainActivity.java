package info.androidhive.slidingmenu.Games.Hangman.gameplay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import info.androidhive.slidingmenu.Games.Hangman.highscore.ControlScore;
import info.androidhive.slidingmenu.Games.Hangman.settings.SettingsActivity;
import info.androidhive.slidingmenu.R;

public class HangmanMainActivity extends Activity implements OnClickListener {
	public ArrayList<String> words;
	List<String> guessedLetters;
	String randomWord;
	int moves;
	int maxMoves;
	int wordLength;
	int maxLength;
	boolean firstmove;
	ControlScore cls;
	ControlWords clw;
	GamePlay gameplay;
	public SharedPreferences gamePrefs;
	public static final String GAME_PREFS = "ArithmeticFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.hangman_activity_main);

		ImageButton settingsButton = (ImageButton) findViewById(R.id.settings);

		settingsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent SettingsIntent = new Intent(HangmanMainActivity.this,
						SettingsActivity.class);
                HangmanMainActivity.this.startActivity(SettingsIntent);
                HangmanMainActivity.this.finish();
			}
		});
		cls = new ControlScore();
		clw = new ControlWords();
		gameplay = new GamePlay(this);
		gamePrefs = getSharedPreferences(GAME_PREFS, 0);
		firstmove = true;
		try {
			words = clw.populateWords(getAssets().open("words.xmf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bundle newGame = getIntent().getExtras();
		if (newGame != null) {
			gameplay.newGame();
		} else {
			guessedLetters = new ArrayList<String>(Arrays.asList(gamePrefs
					.getString("guessedLetters", "[]").replace("[", "")
					.replace("]", "").replace(" ", "").split(",")));
			// get the prefs object.
			SharedPreferences settings = PreferenceManager
					.getDefaultSharedPreferences(this);
			wordLength = settings.getInt("wordLength", 9);

			randomWord = gamePrefs.getString("word",
					clw.getWord(wordLength, words).toUpperCase());
			maxMoves = gamePrefs.getInt("moves",
					settings.getInt("maxMoves", 10));
			moves = gameplay.getMistakes();

			TextView movesLeft = (TextView) findViewById(R.id.moves);
			movesLeft.setText("Moves left: " + (maxMoves - moves));
			populateButtons();
			gameplay.showLetters();
		}

		if (maxMoves - moves <= 0) {
			cls.startHighscore(HangmanMainActivity.this);
		}
	}

	public void populateButtons() {

		GridView keyboard = (GridView) findViewById(R.id.grid);
		/* disable scrolling */
		keyboard.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					return true;
				}
				return false;
			}
		});

		/* add buttons to gridview */
		Button cb = null;
		ArrayList<Button> mButtons = new ArrayList<Button>();

        String[] keys={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","Ä","Ü","Ö","ß"};
		for (String key:keys) {
			cb = new Button(this);
			cb.setText("" + key);
			cb.setPadding(0, 0, 0, 0);
			cb.setId(key.charAt(0));
			cb.setTextColor(Color.parseColor("white"));
			cb.setTextSize(25);
			cb.setOnClickListener(this);
			cb.setBackgroundColor(Color.parseColor("red"));
			if (guessedLetters.contains("" + key)) {
				cb.setBackgroundColor(Color.parseColor("#858585"));
				cb.setOnClickListener(null);
			}
			mButtons.add(cb);
		}

		keyboard.setAdapter(new CustomAdapter(mButtons));
	}

	@Override
	public void onClick(View v) {
		Button selection = (Button) v;
		selection.setBackgroundColor(Color.parseColor("#858585"));
		selection.setOnClickListener(null);
		gameplay.newGuess((String) selection.getText());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent SettingsIntent = new Intent(this, SettingsActivity.class);
			startActivity(SettingsIntent);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		// Store values between instances here
		if (gamePrefs != null) {
			SharedPreferences.Editor editor = gamePrefs.edit();

			editor.putInt("moves", maxMoves);
			editor.putString("word", randomWord);
			editor.putString("guessedLetters", "" + guessedLetters);

			// Commit to storage
			editor.commit();
		}
	}

}
