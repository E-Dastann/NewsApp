package com.example.newsapp.ui.newsapp;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.databinding.ItemNewsBinding;
import com.example.newsapp.ui.NewModel;
import com.example.newsapp.ui.OnItemClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolderNews> {

    private List<NewModel> list = new ArrayList<>();
    private static OnItemClickListener listener;
    private ArrayList<NewModel> arrayListCopy;

    public NewAdapter(List<NewModel> list) {
        this.list = list;
        this.arrayListCopy= new ArrayList<>();
        arrayListCopy.addAll(list);
    }

    public NewAdapter() {
    }

    public void setList(ArrayList<NewModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderNews(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNews holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
/*
    public void addItem(NewModel newModel) {
        list.add(0, newModel);
        notifyItemInserted(0);
    }*/

    public NewModel getItem(int position) {
        return list.get(position);
    }

    public void updateItem(NewModel newModel, int position) {
        list.set(position, newModel);
        notifyItemChanged(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public void removeItem(NewModel newModel) {
        list.remove(newModel);
        notifyDataSetChanged();
    }

    public void addList(List<NewModel> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    class ViewHolderNews extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;
        private EditText TextTitle;

        public ViewHolderNews(@NonNull ItemNewsBinding itemView) {

            super(itemView.getRoot());
            binding = itemView;

        }

        public void bind(NewModel newModel) {
            binding.TextTitle.setText(newModel.getTextTitle());
            if (getAdapterPosition() % 2 == 0) {
                itemView.setBackgroundColor(Color.GRAY);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyy HH:mm:ss", Locale.ROOT);
            String a = sdf.format(newModel.getCreateAt());
            binding.TextTitle.setText(newModel.getTextTitle());
            binding.timeItem.setText(a);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnItemLongClickListener(getAdapterPosition(), newModel);
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.OnItemClick(getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickForSearch {
    }

    public void setOnItemSearchListener(OnItemClickForSearch onItemSearchListener) {

    }

    public void filter(CharSequence charSequence) {
        ArrayList<NewModel> tempArrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(charSequence)){
            for (NewModel newModel :list){
                if (newModel.getTextTitle().toLowerCase().contains(charSequence)){
                    tempArrayList.add(newModel  );
                }
            }
        }else {
            list.addAll(arrayListCopy);

        }


            list.clear();
            list.addAll(tempArrayList);
            notifyDataSetChanged();
            tempArrayList.clear();

    }


}


