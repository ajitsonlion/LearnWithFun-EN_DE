package info.androidhive.slidingmenu.adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

 import info.androidhive.slidingmenu.ListeningHearing.SpeakWord;
import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.CustomUI.FlipAnimation;
import info.androidhive.slidingmenu.model.WordModel.FlashCard;


/**
 * Created by ajit on 22.11.14.
 */
public class CardAdapter extends ArrayAdapter<FlashCard> {

    private final Context context;
    private  ArrayList<FlashCard> flashCards;
    SpeakWord speakWord;


    public CardAdapter(Context context, ArrayList<FlashCard> flashCards) {
        super(context, R.layout.cardslayout);
        this.context = context;
        this.flashCards = flashCards;
        speakWord = new SpeakWord(context);
        Prefs.initPrefs(context);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return flashCards.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.cardslayout, parent, false);
        final View cardFace =  rowView.findViewById(R.id.main_activity_card_face);
        final View cardBack =   rowView.findViewById(R.id.main_activity_card_back);
        cardFace.setMinimumHeight(parent.getMeasuredHeight());
        cardBack.setMinimumHeight(parent.getMeasuredHeight());
        TextView germanWordTextView = (TextView) rowView.findViewById(R.id.germanWord);
        TextView englishWordTextView = (TextView) rowView.findViewById(R.id.englishWord);
        ImageView flipIconFront=(ImageView)rowView.findViewById(R.id.flip_icon_front);
        ImageView flipIconBack=(ImageView)rowView.findViewById(R.id.flip_icon_back);
        final ImageView bookmarkIcon=(ImageView)rowView.findViewById(R.id.imageViewBookmark);
        ImageView iconSpeak=(ImageView)rowView.findViewById(R.id.icon_speak);
        final FlashCard flashCard= flashCards.get(position);

       final boolean bookmark= Prefs.getBoolean("card_"+flashCard.getId(),false);

       if(bookmark){
           bookmarkIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmarked_yes));
       }
        else {
           bookmarkIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmarked_no));
       }

        String germanWord=flashCard.getGer().getWord();

        germanWordTextView.setText(germanWord);
        englishWordTextView.setText(flashCard.getEng().getWord());

        flipIconFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(rowView,cardFace,cardBack);
            }
        });

        flipIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard(rowView,cardFace,cardBack);
            }
        });

        iconSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speakWord.tts.speak(flashCard.getGer().getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        bookmarkIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBookmark();
            }

            private void toggleBookmark() {
                if(bookmark){

                    Prefs.putBoolean("card_"+flashCard.getId(),false);
                    bookmarkIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmarked_no));
                    Toast.makeText(context,"Removed to Bookmarked "+flashCard.getGer().getWord(),Toast.LENGTH_SHORT).show();
                 }
                else {
                     Prefs.putBoolean("card_" + flashCard.getId(), true);
                    bookmarkIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_bookmarked_yes));
                    Toast.makeText(context,"Added from Bookmarked "+flashCard.getGer().getWord(),Toast.LENGTH_SHORT).show();
                }
            }


        });

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        rowView.startAnimation(animation);
        lastPosition = position;


        return rowView;
    }



    public  void flipCard(View view,View cardFace, View cardBack)
    {


        FlipAnimation flipAnimation = new FlipAnimation(cardFace, cardBack);

        if (cardFace.getVisibility() == View.GONE)
        {
            flipAnimation.reverse();
        }
        view.startAnimation(flipAnimation);
    }





}
