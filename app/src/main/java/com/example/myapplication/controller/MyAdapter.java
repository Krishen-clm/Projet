package com.example.myapplication.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.RickAndMortyCharacter;
import com.example.myapplication.view.RickAndMortyCharacterActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {
    private List<RickAndMortyCharacter> values;
    private List<RickAndMortyCharacter> fullValues;
    private Context context;

    @Override
    public Filter getFilter() {
        return Filter;
    }

    private Filter Filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RickAndMortyCharacter> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullValues);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (RickAndMortyCharacter rmc : fullValues){
                    if (rmc.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(rmc);
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            values.clear();
            values.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView im;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            im = v.findViewById(R.id.icon);
        }
    }

    public void add(int position, RickAndMortyCharacter item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<RickAndMortyCharacter> myDataset, Context myContext) {
        fullValues = new ArrayList<RickAndMortyCharacter>(myDataset);
        this.values = myDataset;
        context = myContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        RickAndMortyCharacter rickAndMortyCharacter = values.get(position);
        final String
                name = rickAndMortyCharacter.getName();
        holder.txtHeader.setText(name);
        final String species = rickAndMortyCharacter.getSpecies();
        holder.txtFooter.setText(species);
        final String image = rickAndMortyCharacter.getImage();
        Glide.with(context).load(image).into(holder.im);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RickAndMortyCharacterActivity.class);
                intent.putExtra("character_nom",name);
                intent.putExtra("character_espece",species);
                intent.putExtra("image",image);
                context.startActivity(intent);

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}