package com.ilkrrkck.farfromfat;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class EgzersizlerAdapter extends RecyclerView.Adapter<EgzersizlerAdapter.ViewHolder> implements Serializable {

    private static ArrayList<Egzersizler> localDataSet; //STATİC OLDUĞUNU UNUTMA



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    public static void VeriGetir(ArrayList<Egzersizler> dataArray){
        localDataSet = dataArray;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView setText,tekrarText,isimText;
        private ImageView exImage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            setText = (TextView) view.findViewById(R.id.set_textView);
            tekrarText = (TextView) view.findViewById(R.id.tekrar_textView);
            isimText = (TextView) view.findViewById(R.id.exAd_TextView);
            exImage=(ImageView)view.findViewById(R.id.ex_ImageView);
        }

        public TextView getSetText() {
            return setText;
        }

        public TextView getTekrarText() {
            return tekrarText;
        }

        public TextView getIsimText() {
            return isimText;
        }

        public ImageView getExImage() {
            return exImage;
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet ArrayList<Egzersizler> containing the data to populate views to be used
     * by RecyclerView.
     */
    public EgzersizlerAdapter(ArrayList<Egzersizler> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_egzersiz_menu, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.isimText.setText((int)localDataSet.get(position).getExSet());
        viewHolder.setText.setText((int)localDataSet.get(position).getExSet());
        viewHolder.tekrarText.setText((int)localDataSet.get(position).getExTekrar());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
