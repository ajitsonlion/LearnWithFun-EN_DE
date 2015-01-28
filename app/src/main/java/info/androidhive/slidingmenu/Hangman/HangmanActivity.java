package info.androidhive.slidingmenu.Games.Hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.com.passeionaweb.android.statslib.StatisticsManager;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class HangmanActivity extends Activity {
    private static final int[]          hangmans           = new int[] { R.drawable.hangman0, R.drawable.hangman1, R.drawable.hangman2, R.drawable.hangman3, R.drawable.hangman4, R.drawable.hangman5,
            R.drawable.hangman6, R.drawable.hangman7,     };

    private static Map<String, Integer> lettersMap;

    static {
        lettersMap = new HashMap<String, Integer>();
        lettersMap.put("A", R.id.A);
        lettersMap.put("B", R.id.B);
        lettersMap.put("C", R.id.C);
        lettersMap.put("D", R.id.D);
        lettersMap.put("E", R.id.E);
        lettersMap.put("F", R.id.F);
        lettersMap.put("G", R.id.G);
        lettersMap.put("H", R.id.H);
        lettersMap.put("I", R.id.I);
        lettersMap.put("J", R.id.J);
        lettersMap.put("K", R.id.K);
        lettersMap.put("L", R.id.L);
        lettersMap.put("M", R.id.M);
        lettersMap.put("N", R.id.N);
        lettersMap.put("O", R.id.O);
        lettersMap.put("P", R.id.P);
        lettersMap.put("Q", R.id.Q);
        lettersMap.put("R", R.id.R);
        lettersMap.put("S", R.id.S);
        lettersMap.put("T", R.id.T);
        lettersMap.put("U", R.id.U);
        lettersMap.put("V", R.id.V);
        lettersMap.put("X", R.id.X);
        lettersMap.put("W", R.id.W);
        lettersMap.put("Y", R.id.Y);
        lettersMap.put("Z", R.id.Z);
    }
    private int                         errors             = 0;
    private int                         picks              = 0;
    private ImageView                   hangman;

    private char[]                      picked             = new char[26];
    private String                      word;
    private TextView[]                  views              = null;
    private Typeface                    tf;

    private int                         status;
    private static final int            STATUS_PLAYING     = 0;
    private static final int            STATUS_WON         = 1;
    private static final int            STATUS_LOSE        = 2;

    private static final int            ERRORS_MAX         = 6;

    private static final int            MENU_NEW           = 0;
    private static final int            MENU_DONATE        = 1;
    private static final int            MENU_TYPE_WORD     = 2;
    private static final String         LINK_DONATION      = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=H9FZXU9ADW5ZC&lc=BR&item_name=Rafael%20Roman&item_number=android%2dhangman&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted";

    private static final int            DIALOG_TYPE_WORD   = 0;
    public static final String          PREF_SOUND_ENABLED = "sound_fx";

    private ArrayList<String>           words;

    private StatisticsManager           statsManager       = new StatisticsManager();

    private OnClickListener             pickLetterListener = new OnClickListener() {

                                                               @Override
                                                               public void onClick(View v) {
                                                                   pickLetter((TextView) v);
                                                               }
                                                           };
    private static final String         ADMOB_PUB_ID       = "a14d0bad274b745";
    private boolean                     typedWord          = false;
    private SoundPool                   sp                 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private int                         wrongSoundId       = -1;
    private int                         newGameSoundId     = -1;
    private SharedPreferences           mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the activity FULLSCREEN
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tf = Typeface.createFromAsset(getAssets(), "fonts/Sketched.ttf");
        setContentView(R.layout.hangman);
        hangman = (ImageView) findViewById(R.id.imgHangman);
        populateWords(getIntent().getExtras().getInt(MainActivity.EXTRA_CATEGORY));
        try {
            newGameSoundId = sp.load(getAssets().openFd("sounds/moving.mp3"), 1);
            wrongSoundId = sp.load(getAssets().openFd("sounds/fizzle.mp3"), 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        newGame();
        createAd();
    }

    /**
     * creates and displays the AD unity
     * ADMOB
     */
    private void createAd() {
        // Create the adView
        AdView adView = new AdView(this, AdSize.BANNER, ADMOB_PUB_ID);
        LinearLayout layout = (LinearLayout) findViewById(R.id.adContainer);
        layout.addView(adView);
        // Add the adView to it
        AdRequest request = new AdRequest();
        // Initiate a generic request to load it with an ad
        adView.loadAd(request);
    }

    private void populateWords(int category) {
        switch (category) {
            case MainActivity.CATEGORY_ALL:
                words = joinAllCategories();
                break;
            default:
                String[] values = getResources().getStringArray(MainActivity.CATEGORIES_MAP.get(category));
                words = new ArrayList<String>(Arrays.asList(values));
        }
    }

    private void updateHangman() {
        hangman.setImageResource(hangmans[errors]);
    }

    private void pickLetter(TextView v) {
        if (status == STATUS_PLAYING) {
            if (errors == ERRORS_MAX) {
                lose();
                return;
            }
            char letter = v.getText().charAt(0);
            picked[picks] = letter;
            picks++;
            if (isLetterInWord(letter)) {
                updateLetterPlaces(letter);
            } else {
                errors++;
                updateHangman();
                if (errors == ERRORS_MAX) {
                    lose();
                } else {
                    if (isSoundEnabled()) {
                        sp.play(wrongSoundId, 1, 1, 0, 0, 1);
                    }
                }
            }
            v.setOnClickListener(null);
            v.setTextColor(getResources().getColor(R.color.picked_letter));
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        }
    }

    private boolean isSoundEnabled() {
        return mPrefs.getBoolean(PREF_SOUND_ENABLED, true);
    }

    private void pickLetter(char c) {
        TextView view = (TextView) findViewById(lettersMap.get(String.valueOf(c).toUpperCase()));
        pickLetter(view);
    }

    private boolean isLetterInWord(char letter) {
        return word.indexOf(new String(new char[] { letter })) != -1;
    }

    private void newGame(String word, boolean typed) {
        status = STATUS_PLAYING;
        if (isValidWord(word)) {
            setContentView(R.layout.hangman);
            clear();
            updateHangman();
            views = new TextView[] { (TextView) findViewById(R.id.letterPlace0), (TextView) findViewById(R.id.letterPlace1), (TextView) findViewById(R.id.letterPlace2),
                    (TextView) findViewById(R.id.letterPlace3), (TextView) findViewById(R.id.letterPlace4), (TextView) findViewById(R.id.letterPlace5), (TextView) findViewById(R.id.letterPlace6),
                    (TextView) findViewById(R.id.letterPlace7), (TextView) findViewById(R.id.letterPlace8), (TextView) findViewById(R.id.letterPlace9) };
            hangman = ((ImageView) findViewById(R.id.imgHangman));

            // setting the typeface for all letters
            Set<String> lettersSet = lettersMap.keySet();
            for (String key : lettersSet) {
                TextView letter = ((TextView) findViewById(lettersMap.get(key)));
                letter.setTypeface(tf);
                letter.setOnClickListener(pickLetterListener);
            }
            this.word = word.trim().toUpperCase();
            createLetterPlaces();
            this.typedWord = typed;
        } else {
            Toast.makeText(this, R.string.not_valid_word, Toast.LENGTH_LONG).show();
        }

        if (isSoundEnabled()) {
            sp.play(newGameSoundId, 1, 1, 0, 0, 1);
        }
    }

    private void newGame() {
        newGame(getRandomWord(), false);
    }

    private void updateLetterPlaces(char letter) {
        boolean hasLetter = false;
        for (int i = 0; i < word.length(); i++) {
            TextView text = views[i];
            if (text.getTag() != null && text.getTag().equals(letter)) {
                text.setText("" + letter);
                text.setTag(null);
            }
            if (text.getTag() != null) {
                hasLetter = true;
            }
        }
        if (!hasLetter) {
            win();
        }
    }

    private void createLetterPlaces() {

        for (int i = 0; i < word.length(); i++) {
            TextView text = views[i];
            text.setText("_");
            text.setTag(word.charAt(i));
            text.setVisibility(TextView.VISIBLE);
            text.setTypeface(tf);
        }
    }

    private String getRandomWord() {
        String word = words.get(new Random().nextInt(words.size()));
        if (!isValidWord(word)) {
            return getRandomWord();
        }
        return normalize(word);
    }

    private boolean isValidWord(String word) {
        if (word == null || word == "" || word.length() <= 3) {
            return false;
        }

        word = word.toUpperCase();
        if (word.length() > 10) { // TODO allow a bigger word
            return false;
        } else if (word.indexOf('-') != -1) {// TODO allow hifens
            return false;
        } else if (word.indexOf(' ') != -1) {// TODO allow spaces
            return false;
        } else {
            if (!word.matches("[A-Z]*")) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void clear() {
        errors = 0;
        picks = 0;
        picked = new char[26];
    }

    private void lose() {
        revealWord();
        if (status != STATUS_LOSE) {
            status = STATUS_LOSE;
            if (!typedWord) {
                statsManager.lose(this);
            }
        }
        Toast toast = Toast.makeText(this, R.string.you_lose, Toast.LENGTH_SHORT);
        toast.show();
        showStatisticMessage();
    }

    private void win() {
        if (status != STATUS_WON) {
            status = STATUS_WON;
            if (!typedWord) {
                statsManager.win(this);
            }
        }
        Toast toast = Toast.makeText(this, R.string.you_won, Toast.LENGTH_SHORT);
        toast.show();
        showStatisticMessage();
    }

    private void showStatisticMessage() {
        if (!typedWord) {
            String message = getString(R.string.message_stats);
            message = message.replace("#count#", String.valueOf(statsManager.getGamesCount(this)));
            message = message.replace("#wins#", String.valueOf(statsManager.getWins(this)));
            message = message.replace("#percentage#", String.valueOf(statsManager.getWinPercentage(this)));
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    private String normalize(String word) {
        word = word.toUpperCase();
        word = word.replaceAll("[����]", "A");
        word = word.replaceAll("[����]", "E");
        word = word.replaceAll("[���]", "I");
        word = word.replaceAll("[���]", "O");
        word = word.replaceAll("[���]", "U");
        word = word.replaceAll("[�]", "C");
        return word;
    }

    private void revealWord() {
        for (int i = 0; i < word.length(); i++) {
            TextView text = views[i];
            if (text.getTag() != null) {
                text.setText("" + (Character) text.getTag());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, MENU_NEW, Menu.NONE, R.string.menu_new_game).setIcon(android.R.drawable.ic_menu_add);
        // menu.add(Menu.NONE,MENU_DONATE,Menu.NONE,R.string.menu_donate).setIcon(R.drawable.donate);
        menu.add(Menu.NONE, MENU_TYPE_WORD, Menu.NONE, R.string.menu_type_word).setIcon(android.R.drawable.ic_menu_edit);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_NEW:
                newGame();
                break;
            case MENU_DONATE:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(LINK_DONATION));
                startActivity(intent);
            case MENU_TYPE_WORD:
                showDialog(DIALOG_TYPE_WORD);
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String> joinAllCategories() {
        HashMap<Integer, Integer> map = (HashMap<Integer, Integer>) MainActivity.CATEGORIES_MAP;
        Set<Integer> keys = map.keySet();
        ArrayList<String> all = new ArrayList<String>();
        for (Integer i : keys) {
            String[] cat = getResources().getStringArray(map.get(i));
            for (String value : cat) {
                all.add(value);
            }
        }
        return all;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        char c = (char) event.getUnicodeChar();
        if (Character.isLetter(c)) {
            pickLetter(c);
        } else if (keyCode != KeyEvent.KEYCODE_BACK && keyCode != KeyEvent.KEYCODE_MENU && keyCode != KeyEvent.KEYCODE_CALL && keyCode != KeyEvent.KEYCODE_CAMERA && keyCode != KeyEvent.KEYCODE_HOME
                && keyCode != KeyEvent.KEYCODE_SEARCH && keyCode != KeyEvent.KEYCODE_POWER && keyCode != KeyEvent.KEYCODE_VOLUME_UP && keyCode != KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.only_letters), Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_TYPE_WORD:
                final EditText wordInput = new EditText(this);
                wordInput.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(wordInput);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setTitle(R.string.menu_type_word);
                builder.setPositiveButton(R.string.dialog_word_ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame(wordInput.getText().toString(), true);
                    }

                });
                final Dialog dialog = builder.create();
                return dialog;
        }
        return super.onCreateDialog(id);

    }

}
