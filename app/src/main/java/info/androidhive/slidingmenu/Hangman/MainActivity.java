package info.androidhive.slidingmenu.Games.Hangman;

import java.util.HashMap;
import java.util.Map;

import br.com.passeionaweb.android.billing.util.UpgradeChecker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int                  DIALOG_CATEGORY      = 0;
    private int                               category             = 0;
    public static final String                EXTRA_CATEGORY       = "category";
    public static final int                   CATEGORY_ALL         = 0;
    public static final int                   CATEGORY_ANIMALS     = 1;
    public static final int                   CATEGORY_OCCUPATIONS = 2;
    public static final int                   CATEGORY_COUNTRIES   = 3;
    public static final int                   CATEGORY_FRUITS      = 4;

    public static final String                LINK_FORCANOAD       = "market://details?id=br.com.passeionaweb.android.hangmanPRO";

    public static final Map<Integer, Integer> CATEGORIES_MAP;

    static {
        CATEGORIES_MAP = new HashMap<Integer, Integer>();
        CATEGORIES_MAP.put(CATEGORY_ANIMALS, R.array.animals);
        CATEGORIES_MAP.put(CATEGORY_OCCUPATIONS, R.array.occupations);
        CATEGORIES_MAP.put(CATEGORY_COUNTRIES, R.array.countries);
        CATEGORIES_MAP.put(CATEGORY_FRUITS, R.array.fruits);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make the activity FULLSCREEN
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Sketched.ttf");

        ((TextView) findViewById(R.id.txtTitle)).setTypeface(tf);
        TextView linkCategory = (TextView) findViewById(R.id.txtCategory);
        linkCategory.setTypeface(tf);
        linkCategory.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DIALOG_CATEGORY);
            }
        });

        TextView linkBegin = ((TextView) findViewById(R.id.lnkBegin));
        linkBegin.setTypeface(tf);

        linkBegin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        TextView linkStats = ((TextView) findViewById(R.id.lnkStats));
        linkStats.setTypeface(tf);

        linkStats.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showStats();
            }
        });

        UpgradeChecker.showUpgradeDialog(this);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_CATEGORY:
                return new AlertDialog.Builder(this).setTitle(R.string.dialog_category_title).setItems(R.array.categories, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        category = which;
                        startGame();
                    }
                }).create();
            case UpgradeChecker.DIALOG_UPGRADE:
                return UpgradeChecker.getUpgradeDialog(this);
        }
        return super.onCreateDialog(id);
    }

    private void startGame() {
        Intent intent = new Intent(getApplicationContext(), HangmanActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        startActivity(intent);
    }

    public void showStats() {
        Intent intent = new Intent(getApplicationContext(), StatisticsActivity.class);
        startActivity(intent);
    }

}
