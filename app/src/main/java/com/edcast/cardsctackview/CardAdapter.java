package com.edcast.cardsctackview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by saadati on 10/4/14.
 */
public class CardAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View container = inflater.inflate(R.layout.my_card, viewGroup, false);
        final TextView tv = (TextView)container.findViewById(R.id.textView);
        Button button= (Button)container.findViewById(R.id.button);
        tv.setText("This is card #" + (i+1));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("You clicked on button #" + (i+1));
            }
        });
        return container;
    }
}
