package com.example.csc413_volley_template.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.csc413_volley_template.app.App;
import com.example.csc413_volley_template.model.events;
import com.example.csc413_volley_template.R;
import com.example.csc413_volley_template.volley.VolleySingleton;

import java.util.List;

/*
 * Created by abhijit on 11/20/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<events> movieList;
    private OnClickListener listener;

    public RecyclerViewAdapter(List<events> movieList) {
        this.movieList = movieList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card_layout, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        events events = movieList.get(position);
        CardViewHolder cardViewHolder = (CardViewHolder) holder;

        cardViewHolder.setTitle(events.getName());
        cardViewHolder.setCity(events.getCity());
        cardViewHolder.setMembers(events.getMembers());
        cardViewHolder.setPosterUrl(events.getPhoto_link());
        cardViewHolder.setGroup_id(events.getId());
        if(listener!=null) {
            cardViewHolder.bindClickListener(listener, events);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    /**
     * Removes older data from movieList and update it.
     * Once the data is updated, notifies RecyclerViewAdapter.
     * @param modelList list of movies
     */
    public void updateDataSet(List<events> modelList) {
        this.movieList.clear();
        this.movieList.addAll(modelList);
        notifyDataSetChanged();
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public interface OnClickListener {
        void onCardClick(events movie);
        void onPosterClick(events movie);
    }

    /**
     *  CardViewHolder will hold the layout of the each item in the RecyclerView.
     */
    private class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView title;
        private TextView city;
        private TextView members;
        private NetworkImageView poster;
        private TextView group_id;

        /**
         * Class constructor.
         * @param view  layout of each item int the RecyclerView
         */
        CardViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view.findViewById(R.id.card_view);
            this.title = (TextView) view.findViewById(R.id.tvTitle);
            this.city = (TextView) view.findViewById(R.id.tvYear);
            this.poster = (NetworkImageView) view.findViewById(R.id.nivPoster);
            this.members = (TextView) view.findViewById(R.id.Attendees);
            this.group_id = (TextView) view.findViewById(R.id.group_id);
        }

        /**
         * append title text to Title:
         * @param title String of Title of movie
         */
        void setTitle(String title) {
            String t = "Name:\n" + title;
            this.title.setText(t);
        }

        /**
         * append city text to Release Year:
         * @param city String of city of release
         */
        void setCity(String city) {
            String y = "City:\n" + city;
            this.city.setText(y);
        }

        void setMembers(String members){
            String z = "Members:\n" +members;
            this.members.setText(z);
        }
        void setGroup_id(String group_id){
            String xy ="ID:\n" + group_id;
            this.group_id.setText(xy);
        }

        /**
         * Sends ImageRequest using volley using imageLoader and Cache.
         * This is pre-implemented feature of Volley to cache images for faster responses.
         * Check VolleySingleton class for more details.
         *
         */
        void setPosterUrl(String imageUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.poster.setImageUrl(imageUrl, imageLoader);
        }

        /**
         *  @param listener {@link OnClickListener}
         * @param events
         */
        void bindClickListener(final OnClickListener listener, final events events){
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClick(events);
                }
            });

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPosterClick(events);
                }
            });
        }
    }
}
