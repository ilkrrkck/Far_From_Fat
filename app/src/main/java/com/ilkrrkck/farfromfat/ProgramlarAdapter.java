package com.ilkrrkck.farfromfat;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProgramlarAdapter extends RecyclerView.Adapter<ProgramlarAdapter.ProgramlarHolder> {
    private ArrayList<Programlar> programlarYerel;
    private Context context;
    private OnItemClickListener listener;

    public ProgramlarAdapter(ArrayList<Programlar> programlarYerel, Context context) {
        this.programlarYerel = programlarYerel;
        this.context = context;
    }


    @NonNull
    @Override
    public ProgramlarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //görünümlerin ayarlandığı yer
        View v = LayoutInflater.from(context).inflate(R.layout.programlar,parent,false);
        return new ProgramlarHolder(v); // inner classın constructerındaki parametrede v oldğundan oraya gönderiyor
        // yukarıdaki nesnelerle inner class nesnleri arası bağlantı kurabiliyoruz.
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramlarHolder holder, int position) {
        //içeriğin dolduruldupu yer
        Programlar program = programlarYerel.get(position); // görünüm eklendikçe içerideki pozisyon artmaya devam eder (aşağı doğru inmek için galiba)
        holder.setData(program); // doldurduğumuz programlar nesnesi. İçinde iki program da var
    }

    @Override
    public int getItemCount() {
        return programlarYerel.size();
    }

    class ProgramlarHolder extends RecyclerView.ViewHolder{
        TextView programTextView;
        ImageView programImageView;


        public ProgramlarHolder(@NonNull View itemView) {
            super(itemView);
            programImageView=(ImageView)itemView.findViewById(R.id.program_ImageView);
            programTextView=(TextView)itemView.findViewById(R.id.program_TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener !=null && position !=RecyclerView.NO_POSITION)
                        listener.onItemClick(programlarYerel.get(position),position);
                }
            });
        } // TIKLAMA EYLEMİ BURADAKİ VİEW ÜSTÜNE OLACAK

        public void setData(Programlar program){
            this.programTextView.setText(program.getProg_Ad());
            this.programImageView.setBackgroundResource(R.drawable.anamenu); //BURAYA BAŞKA RESİMM GELECEK
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Programlar program,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
