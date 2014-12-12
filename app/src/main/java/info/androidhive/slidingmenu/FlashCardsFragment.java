package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;

import info.androidhive.slidingmenu.WordSearch.ClearableAutoCompleteTextView;
import info.androidhive.slidingmenu.WordSearch.SearchInDictionary;
import info.androidhive.slidingmenu.adapter.CardAdapter;
import info.androidhive.slidingmenu.cardUI.ProgressWheel;
import info.androidhive.slidingmenu.cardUI.SingleScrollListView;
import info.androidhive.slidingmenu.model.WordModel.Categories;
import info.androidhive.slidingmenu.model.WordModel.FlashCard;

public class FlashCardsFragment extends Fragment {


    ProgressDialog mProgressDialog;
    ArrayList<FlashCard> wordsDatabase=new ArrayList<FlashCard>();

    SearchInDictionary searchInDictionary;
    SingleScrollListView flipView;
    private ClearableAutoCompleteTextView searchBox;
	
	public FlashCardsFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ProgressWheel pw = (ProgressWheel)rootView.findViewById(R.id.pw_spinner);
        pw.setProgress(180);

        flipView = (SingleScrollListView) rootView.findViewById(R.id.flip_view);
        ArrayList<FlashCard> flashCards=new ArrayList<FlashCard>();

        Bundle bundle=this.getArguments();
        int temp=bundle.getInt("categoryID");
        Categories categories=MainActivity.wordByCategories.get(temp);

        flashCards= categories.getCardsForCategory();

      // flashCards.addAll(MainActivity.wordByCategories.get(bundle.getInt("categoryID")).getCards());


        CardAdapter cardAdapter=new CardAdapter(getActivity(),flashCards);
        flipView.setAdapter(cardAdapter);
        // start with the text view hidden in the action ba

        flipView.setSingleScroll(true);

        return rootView;
    }

}
