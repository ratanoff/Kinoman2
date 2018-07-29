package ru.ratanov.kinoman.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.ratanov.kinoman.model.TopFilm;
import ru.ratanov.kinoman.view.TopItemView;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private Context context;
    private List<TopFilm> items;

    public TopAdapter(Context context, List<TopFilm> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = new TopItemView(context);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        holder.bindItem(items.get(position).getPosterUrl());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class TopViewHolder extends RecyclerView.ViewHolder {

        public TopViewHolder(View itemView) {
            super(itemView);
        }

        public void bindItem(String url) {
            Picasso.get()
                    .load(url)
                    .into((ImageView) itemView);
        }
    }
}
