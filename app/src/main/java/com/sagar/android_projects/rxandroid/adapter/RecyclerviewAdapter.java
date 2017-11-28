package com.sagar.android_projects.rxandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sagar.android_projects.rxandroid.MainActivity;
import com.sagar.android_projects.rxandroid.databinding.RecyclerviewItemBinding;
import com.sagar.android_projects.rxandroid.pojo.RecyclerViewItemPojo;

import java.util.ArrayList;

/**
 * Created by sagar on 11/27/2017.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.Viewholder> {
    ArrayList<RecyclerViewItemPojo> recyclerViewItemPojos;
    private Context context;

    public RecyclerviewAdapter(ArrayList<RecyclerViewItemPojo> recyclerViewItemPojos, Context context) {
        this.recyclerViewItemPojos = recyclerViewItemPojos;
        this.context = context;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.bind(recyclerViewItemPojos.get(position));
    }

    @Override
    public int getItemCount() {
        return recyclerViewItemPojos.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private RecyclerviewItemBinding recyclerviewItemBinding;

        Viewholder(RecyclerviewItemBinding recyclerviewItemBinding) {
            super(recyclerviewItemBinding.getRoot());
            this.recyclerviewItemBinding = recyclerviewItemBinding;
        }

        void bind(RecyclerViewItemPojo recyclerViewItemPojo) {
            recyclerviewItemBinding.setData(recyclerViewItemPojo);
            recyclerviewItemBinding.setIndex(getAdapterPosition());
            recyclerviewItemBinding.setContext((MainActivity) context);
            recyclerviewItemBinding.executePendingBindings();
        }
    }
}
