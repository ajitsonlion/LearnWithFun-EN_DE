package info.androidhive.slidingmenu.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.WordModel.FlashCard;

/**
 * Created by ajit on 28.11.14.
 */
public class SearchInDictionary extends ArrayAdapter<FlashCard> implements Filterable {

    private final Context context;
    private ArrayList<FlashCard> originalFlashCards;
    private List<FlashCard> filteredCards=new ArrayList<FlashCard>();

     public SearchInDictionary(Context context, ArrayList<FlashCard> originalFlashCards) {

        super(context, R.layout.search_item);
        this.originalFlashCards = originalFlashCards;
        this.context = context;
         filteredCards=originalFlashCards;
    }


    public int getCount()
    {
        return filteredCards.size();
    }

    //This should return a data object, not an int
    public FlashCard getItem(int position)
    {
        return filteredCards.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                FilterResults results = new FilterResults();

                //If there's nothing to filter on, return the original data for your list
                if(charSequence == null || charSequence.length() == 0)
                {
                    results.values = originalFlashCards;
                    results.count = originalFlashCards.size();
                }
                else
                {
                    filteredCards = new ArrayList<FlashCard>();

                    long startTime = System.nanoTime();
                    for(FlashCard card : originalFlashCards)
                    {
                        //In this loop, you'll filter through originalData and compare each item to charSequence.
                        //If you find a match, add it to your new ArrayList
                        //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional
                        if(card.getGer().getWord().toLowerCase().contains(charSequence.toString().toLowerCase()) ||card.getEng().getWord().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        {
                            filteredCards.add(card);
                        }
                    }
                    long endTime = System.nanoTime();
                    long duration = endTime - startTime;
                    System.out.println("useList:  " + duration / 1000000);

                    results.values = filteredCards;
                    results.count = filteredCards.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {


                notifyDataSetChanged();
            }
        };
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView =inflater.inflate(R.layout.search_item,parent,false);

        TextView german=(TextView)rootView.findViewById(R.id.search_item_venue_name);
        TextView english=(TextView)rootView.findViewById(R.id.search_item_venue_address);

        german.setText(filteredCards.get(position).getGer().getWord());

        english.setText(filteredCards.get(position).getEng().getWord());

        return  rootView;
    }


}
