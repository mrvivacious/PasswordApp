package com.example.cqdeveloper.passwordgenerator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cqdeveloper on 5/25/17.
 */

public class AccountAdapter extends ArrayAdapter<Account>{
    public AccountAdapter(Activity context, ArrayList<Account> accounts) {
        super(context, 0, accounts);
    }

    /**
     * SOURCE: https://github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Account currentAccount = getItem(position);

        // Find the TextView in the list_item.xml layout with the Account title
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.tv_account_title);

        // Get the title from the current Habit object and
        // set this text on the title TextView
        nameTextView.setText(currentAccount.getName());

        // Find the TextView in the list_item.xml layout with the Account password
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.tv_account_password);

        // Get the statistic from the current Habit object and
        // set this text on the number TextView
        numberTextView.setText(currentAccount.getPassword());

//?? Use this for longPress trigger of a "delete this?" fragment
        //also learn fragments in the meanwhile
//        listItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent password = new Intent(v.getContext(), MainActivity.class);
//                v.getContext().startActivity(password);
//            }
//        });
        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
