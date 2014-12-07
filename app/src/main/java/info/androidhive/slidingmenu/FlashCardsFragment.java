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
        View v = inflater.inflate(R.layout.actionbar_search, null);
        // inflate the view that we created before
        // the view that contains the search "magnifier" icon
        final ImageView searchIcon = (ImageView) v.findViewById(R.id.search_icon);
        ActionBar actionBar = getActivity().getActionBar(); // you can use ABS or the non-bc ActionBar
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME
                    | ActionBar.DISPLAY_HOME_AS_UP); // what's mainly important here is DISPLAY_SHOW_CUSTOM. the rest is optional
        }
        flipView = (SingleScrollListView) rootView.findViewById(R.id.flip_view);
        searchBox = (ClearableAutoCompleteTextView) v.findViewById(R.id.search_box);

        ArrayList<FlashCard> flashCards=CategoriesFragment.wordByCategories.get(savedInstanceState.getInt("categoryID")).getFlashCards();

        CardAdapter cardAdapter=new CardAdapter(getActivity(),flashCards);
        flipView.setAdapter(cardAdapter);
        // start with the text view hidden in the action bar
        searchBox.setVisibility(View.INVISIBLE);
        searchIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleSearch(false);
            }
        });

        searchBox.setOnClearListener(new ClearableAutoCompleteTextView.OnClearListener() {

            @Override
            public void onClear() {
                toggleSearch(true);
            }
        });

        searchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // handle clicks on search resaults here
            }

        });

        actionBar.setCustomView(v);



        CardAdapter dictionary = new CardAdapter(getActivity(), wordsDatabase);

        flipView.setAdapter(dictionary);


        searchInDictionary = new SearchInDictionary(getActivity().getApplicationContext(), wordsDatabase);


        searchBox.setAdapter(searchInDictionary);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        flipView.setSingleScroll(true);


        // inflate the view that we created before

        // the view that contains the new clearable autocomplete text view
        searchBox.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));


    }

    // to show search icon - reset = true
// to show search box - reset = false
    protected void toggleSearch(boolean reset) {
        ClearableAutoCompleteTextView searchBox = (ClearableAutoCompleteTextView) getActivity().findViewById(R.id.search_box);
        ImageView searchIcon = (ImageView) getActivity().findViewById(R.id.search_icon);
        if (reset) {
            // hide search box and show search icon
            searchBox.setText("");
            searchBox.setVisibility(View.GONE);
            searchIcon.setVisibility(View.VISIBLE);
            // hide the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
        } else {
            // hide search icon and show search box
            searchIcon.setVisibility(View.GONE);
            searchBox.setVisibility(View.VISIBLE);
            searchBox.requestFocus();
            // show the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
        }

    }


}
