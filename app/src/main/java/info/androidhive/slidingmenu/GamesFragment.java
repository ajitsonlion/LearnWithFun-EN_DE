package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

 import info.androidhive.slidingmenu.Games.WordSearch.wordsearch.view.WordSearchActivity;
import info.androidhive.slidingmenu.adapter.WordCategoriesAdaptor;
import info.androidhive.slidingmenu.model.CategoriesItem;

public class GamesFragment extends Fragment {

    private GridView gridView;
    Activity activity;
    String[] gamesInGerman;
    String[] gamesInEnglish;
    TypedArray iconForGames;
    private ArrayList<CategoriesItem> gameItems;


    public GamesFragment(){}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_categories, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView1);

        gamesInEnglish =getActivity().getResources().getStringArray(R.array.games_items_eng);
        gamesInGerman =getActivity().getResources().getStringArray(R.array.games_items_ger);
        iconForGames =getActivity().getResources().obtainTypedArray(R.array.games_icons);

        gameItems = new ArrayList<CategoriesItem>();

        for (int i=0;i<gamesInEnglish.length;i++){

            gameItems.add(new CategoriesItem(gamesInEnglish[i], iconForGames.getResourceId(i, -1), gamesInGerman[i]));

        }

        gridView.setAdapter(new WordCategoriesAdaptor(getActivity(), gameItems));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


              /*  Bundle bundle=new Bundle();
                bundle.putInt("categoryID",position);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                HangManFragment gamesFragment = new HangManFragment();
                gamesFragment.setArguments(bundle);
                ft.replace(R.id.frame_container, gamesFragment);
                ft.commit();*/
                Intent myIntent;
                switch (position){


                    case 1:
                         myIntent = new Intent(getActivity(), WordSearchActivity.class);
                        getActivity().startActivity(myIntent);
                        break;

                    default:
                        myIntent = new Intent(getActivity(), WordSearchActivity.class);
                        getActivity().startActivity(myIntent);
                        break;
                }


            }
        });

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




}
