package info.androidhive.slidingmenu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.slidingmenu.R;
import info.androidhive.slidingmenu.model.CategoriesItem;
import info.androidhive.slidingmenu.model.NavDrawerItem;

/**
 * Created by ajit on 07.12.14.
 */
public class CategoriesAdaptor extends BaseAdapter {

    private Context context;
    ArrayList<CategoriesItem> categoriesItems;

    public CategoriesAdaptor(Context context, ArrayList<CategoriesItem> categoriesItems){
        this.context = context;
        this.categoriesItems=categoriesItems;

    }

    @Override
    public int getCount() {
        return categoriesItems.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.categories_each_item_layout, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView_categories);
        TextView english = (TextView) convertView.findViewById(R.id.textView_english_categories);
        TextView german = (TextView) convertView.findViewById(R.id.textView_german_categories);

        icon.setImageResource(categoriesItems.get(position).getIcon());
        english.setText(categoriesItems.get(position).getEnglish());
        german.setText(categoriesItems.get(position).getGerman());

        // displaying count


        return convertView;
    }

}
