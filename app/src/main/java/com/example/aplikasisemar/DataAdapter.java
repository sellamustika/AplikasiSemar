package com.example.aplikasisemar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyData> myData;

    public DataAdapter(Context c, ArrayList<MyData> p) {
        context = c ;
        myData = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myData.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myData.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myData.get(i).getDatedoes());


    }

    @Override
    public int getItemCount() {

        return myData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titledoes,descdoes,datedoes;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            descdoes =  (TextView) itemView.findViewById(R.id.descdoes);
            datedoes =  (TextView) itemView.findViewById(R.id.datedoes);

        }
    }
}
