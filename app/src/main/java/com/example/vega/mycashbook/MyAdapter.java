package com.example.vega.mycashbook;

/**
 * Created by Malak Diana Dewi on 10/12/2018.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> rvId, rvTgl, rvJns, rvKet, rvJml;
    public MyAdapter(ArrayList<String> inputId,ArrayList<String> inputTanggal,ArrayList<String> inputKeterangan,ArrayList<String> inputJumlah, ArrayList<String> inputJenis) {
        rvId = inputId;
        rvTgl = inputTanggal;
        rvKet = inputKeterangan;
        rvJml=inputJumlah;
        rvJns = inputJenis;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvSubtitle;
        public  TextView tvJum;
        public  TextView tvJns;
        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.txttgl);
            tvSubtitle = (TextView) v.findViewById(R.id.txtket);
            tvJum = (TextView) v.findViewById(R.id.txtjml);
            tvJns = (TextView) v.findViewById(R.id.txtjns);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_flow, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        final String name = rvTgl.get(position);
        holder.tvTitle.setText(rvTgl.get(position));
        holder.tvSubtitle.setText(rvKet.get(position));
        holder.tvJum.setText(rvJml.get(position));
        holder.tvJns.setText(rvJns.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),LihatCatatan.class);
                i.putExtra("id",rvId.get(position));

                view.getContext().startActivity(i);
            }
        });
        //int[] i=new int[10];
    }
    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di Recycler
        return rvTgl.size();
    }
}
