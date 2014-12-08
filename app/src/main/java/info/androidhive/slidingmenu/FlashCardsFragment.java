package info.androidhive.slidingmenu;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.slidingmenu.WordSearch.ClearableAutoCompleteTextView;
import info.androidhive.slidingmenu.WordSearch.SearchInDictionary;
import info.androidhive.slidingmenu.adapter.CardAdapter;
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

        flipView = (SingleScrollListView) rootView.findViewById(R.id.flip_view);
        ArrayList<FlashCard> flashCards=new ArrayList<FlashCard>();

        Bundle bundle=this.getArguments();

       flashCards.addAll(MainActivity.wordByCategories.get(bundle.getInt("categoryID")).getFlashCards());

        CardAdapter cardAdapter=new CardAdapter(getActivity(),flashCards);
        flipView.setAdapter(cardAdapter);
        // start with the text view hidden in the action ba

        flipView.setSingleScroll(true);

        return rootView;
    }




}
