package com.barisbalcikoca.bitirmeprojesi.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.barisbalcikoca.bitirmeprojesi.data.entity.CRUDCevap;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.YemeklerCevap;
import com.barisbalcikoca.bitirmeprojesi.retrofit.YemeklerDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi = new MutableLiveData<>();

    private YemeklerDao ydao;


    public YemeklerDaoRepository(YemeklerDao ydao){
        this.ydao = ydao;
    }

    public void yemekleriYukle(){
        ydao.yemekerliYukle().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> yemek = response.body().getYemekler();
                yemeklerListesi.setValue(yemek);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {
            }
        });
    }

    public void sepeteYemekEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi){
        ydao.sepeteYemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });

    }

}
