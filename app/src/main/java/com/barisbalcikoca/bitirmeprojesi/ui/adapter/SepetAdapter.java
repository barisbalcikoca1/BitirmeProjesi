package com.barisbalcikoca.bitirmeprojesi.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.barisbalcikoca.bitirmeprojesi.R;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.databinding.AnasayfaCardTasarimBinding;
import com.barisbalcikoca.bitirmeprojesi.databinding.SepetCardTasarimBinding;
import com.barisbalcikoca.bitirmeprojesi.ui.fragment.SepetFragment;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.DetayViewModel;
import com.barisbalcikoca.bitirmeprojesi.ui.viewmodel.SepetViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SepetAdapter extends RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>{

    private Context mContext;
    private List<SepetYemekler> sepetYemeklerListesi;

    private SepetViewModel viewModel;

    public SepetAdapter(Context mContext, List<SepetYemekler> sepetYemeklerListesi, SepetViewModel viewModel) {
        this.mContext = mContext;
        this.sepetYemeklerListesi = sepetYemeklerListesi;
        this.viewModel = viewModel;
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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SepetCardTasarimTutucu holder, int position) {

        SepetYemekler sepetYemekler = sepetYemeklerListesi.get(position);
        SepetCardTasarimBinding t = holder.tasarim;

        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+sepetYemekler.getYemek_resim_adi();
        Glide.with(mContext).load(url).into(t.ivYemek);

        if(sepetYemeklerListesi.size()>0)
        {
            t.tvYemekAdi.setText(sepetYemekler.getYemek_adi());
            t.tvYemekFiyat.setText(sepetYemekler.getYemek_fiyat()+" ₺");
            t.tvYemekAdet.setText(String.valueOf(sepetYemekler.getYemek_siparis_adet()));
            t.tvToplamFiyat.setText(String.valueOf(sepetYemekler.getYemek_fiyat()*sepetYemekler.getYemek_siparis_adet()));



            t.btnDelete.setOnClickListener(view -> {

                if(sepetYemekler.getYemek_siparis_adet()>1){
                    sepetYemekler.setYemek_siparis_adet(sepetYemekler.getYemek_siparis_adet() -1);
                    t.tvYemekAdet.setText(String.valueOf(sepetYemekler.getYemek_siparis_adet()));
                    t.tvToplamFiyat.setText(String.valueOf(sepetYemekler.getYemek_fiyat() * sepetYemekler.getYemek_siparis_adet()));

                }
                else{Snackbar.make(view,sepetYemekler.getYemek_adi()+" silinsin mi?",Snackbar.LENGTH_SHORT)
                        .setAction("Evet",view1 -> {
                            viewModel.sepettenYemekSil(sepetYemekler.getSepet_yemek_id(),sepetYemekler.getKullanici_adi());
                            sepetYemeklerListesi.remove(sepetYemekler);
                        })
                        .setActionTextColor(R.color.acikBeyaz)
                        .setBackgroundTint(R.color.acikSari)
                        .show();
                }
                viewModel.tutarHesapla(sepetYemekler.getYemek_fiyat() , sepetYemekler.getYemek_siparis_adet());
            });

        }else{

            t.tvToplamFiyat.setText("0");
            t.tvYemekAdi.setVisibility(View.GONE);
            t.tvYemekAdet.setVisibility(View.GONE);
            t.tvYemekFiyat.setVisibility(View.GONE);
            t.textViewAdet.setVisibility(View.GONE);
            t.textViewFiyat.setVisibility(View.GONE);
            t.btnDelete.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return sepetYemeklerListesi.size();
    }


}
