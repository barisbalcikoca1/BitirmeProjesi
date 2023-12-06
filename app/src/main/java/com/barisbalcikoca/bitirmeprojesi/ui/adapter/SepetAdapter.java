package com.barisbalcikoca.bitirmeprojesi.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.AnasayfaCardTasarimBinding;
import com.barisbalcikoca.bitirmeprojesi.databinding.SepetCardTasarimBinding;

import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>{

    private Context mContext;
    private List<SepetYemekler> sepetYemeklerListesi;

    public SepetAdapter(Context mContext, List<SepetYemekler> sepetYemeklerListesi) {
        this.mContext = mContext;
        this.sepetYemeklerListesi = sepetYemeklerListesi;
    }
    public class SepetCardTasarimTutucu extends RecyclerView.ViewHolder{
        private SepetCardTasarimBinding tasarim;

        public SepetCardTasarimTutucu(SepetCardTasarimBinding tasarim){
            super(tasarim.getRoot());
            this.tasarim = tasarim;

        }
    }

    @NonNull
    @Override
    public SepetCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetCardTasarimBinding binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new SepetAdapter.SepetCardTasarimTutucu(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetCardTasarimTutucu holder, int position) {

        SepetYemekler sepetYemekler = sepetYemeklerListesi.get(position);
        SepetCardTasarimBinding t = holder.tasarim;



    }

    @Override
    public int getItemCount() {
        return sepetYemeklerListesi.size();
    }


}
