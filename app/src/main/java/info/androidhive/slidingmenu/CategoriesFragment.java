package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import info.androidhive.slidingmenu.adapter.WordCategoriesAdaptor;
import info.androidhive.slidingmenu.model.CategoriesItem;
import info.androidhive.slidingmenu.model.WordModel.Categories;


public class CategoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private GridView gridView;
    Activity activity;
    String[] categoriesInGerman;
    String[] categoriesInEnglish;
    TypedArray iconForCategory;
    private ArrayList<CategoriesItem> categoriesItems;


    public CategoriesFragment() {
        // Required empty public constructor
    }

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

 iconForCategory=getActivity().getResources().obtainTypedArray(R.array.categories_icon);

        categoriesItems = new ArrayList<CategoriesItem>();

        int i=0;
       for (Categories c:MainActivity.wordByCategories){

           categoriesItems.add(new CategoriesItem(c.getCatEng(),iconForCategory.getResourceId(i++, -1),c.getCatGer()));

       }

        gridView.setAdapter(new WordCategoriesAdaptor(getActivity(),categoriesItems));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                Bundle bundle=new Bundle();
                bundle.putInt("categoryID",position);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                FlashCardsFragment flashCardsFragment = new FlashCardsFragment();
                flashCardsFragment.setArguments(bundle);
                ft.replace(R.id.frame_container, flashCardsFragment);
                ft.commit();

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
