package com.futuromovil.inspeccionvehicular.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.futuromovil.inspeccionvehicular.R;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.domain.model.ObjetoPrueba;

import java.util.ArrayList;
import java.util.List;

public class VinListDataAdapter extends RecyclerView.Adapter<VinListDataAdapter.SingleItemRowHolder> {

    private List<WsVehicle> itemsList;
    private Context mContext;
    public OnVinListDataAdapterClickListener mlistener;
    ArrayList<ObjetoPrueba> eventos;

    public VinListDataAdapter(OnVinListDataAdapterClickListener mlistener, Context context, List<WsVehicle> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
        this.mlistener = mlistener;
    }

    public interface OnVinListDataAdapterClickListener {
        void onVinListDataAdapterClicked(WsVehicle wsVehicle);
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vin, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        WsVehicle wsVehicle = itemsList.get(i);
        holder.tvVin.setText(wsVehicle.getVin());
    }


    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView tvVin;

        public SingleItemRowHolder(View view) {
            super(view);
            this.tvVin = (TextView) view.findViewById(R.id.tvVin);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onVinListDataAdapterClicked(itemsList.get(getPosition()));
        }
    }



}
