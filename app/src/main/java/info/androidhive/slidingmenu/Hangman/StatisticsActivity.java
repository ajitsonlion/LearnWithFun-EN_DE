package info.androidhive.slidingmenu.Games.Hangman;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import br.com.passeionaweb.android.statslib.StatisticsManager;

public class StatisticsActivity extends Activity {

    private StatisticsManager stats = new StatisticsManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.statistics);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.txtCount)).setText(getResources().getString(R.string.game_count) + stats.getGamesCount(getApplicationContext()));
        ((TextView) findViewById(R.id.txtWins)).setText(getResources().getString(R.string.wins) + stats.getWins(getApplicationContext()));
        ((TextView) findViewById(R.id.txtLoses)).setText(getResources().getString(R.string.loses) + stats.getLoses(getApplicationContext()));
        ((TextView) findViewById(R.id.txtPercentage)).setText(getResources().getString(R.string.percentage) + stats.getWinPercentage(getApplicationContext())+"%");
    }

}
