package com.example.recycleview_click_listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleview_click_listener.model.City;

import java.util.List;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private List<City> cities;
    private int rowLayout;
    private Context mContext;
    private ItemClickListener clickListener;

    public CityAdapter(List<City> cities, int rowLayout, Context context) {
        this.cities = cities;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final City city = cities.get(position);
        viewHolder.cityName.setText(city.name);
        viewHolder.cityImage.setImageDrawable(mContext.getResources().getDrawable(city.getImageResourceId(mContext)));
    }

    @Override
    public int getItemCount() {
        return cities == null ? 0 : cities.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView cityName;
        public ImageView cityImage;
        public ViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.city_name);
            cityImage = (ImageView)itemView.findViewById(R.id.city_image);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
