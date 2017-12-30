package com.musicarray.codeclan.blackjack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by user on 12/30/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Hand playerHand;

    public ImageAdapter(Context c, Hand hand) {
        mContext = c;
        playerHand = hand;
    }

    public int getCount() {
        return mThumbIds(playerHand).size();
    }


    public Object getItem(int position) {
        return mThumbIds(playerHand).get(position);
    }

    public long getItemId(int position) {
        return mThumbIds(playerHand).get(position);
    }

    public ArrayList<Integer> mThumbIds(Hand hand) {
        ArrayList<Integer> imageIDs = new ArrayList<>();
        for (Card card : hand.getCardsHeld()){
            int drawableResourceId = mContext.getResources().getIdentifier(card.getCardPicture(), "drawable", mContext.getPackageName());
            imageIDs.add(drawableResourceId);
        }
        return imageIDs;
    }



    public View getView(int position,View convertView,ViewGroup parent) {
        ImageView imageView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_image, parent, false);
            imageView = (ImageView) convertView.findViewById(R.id.card_view);
        } else {
            imageView = (ImageView) convertView;
        }
        Integer currentCardId = mThumbIds(this.playerHand).get(position);
        imageView.setImageResource(currentCardId);
        return imageView;
    }









}

