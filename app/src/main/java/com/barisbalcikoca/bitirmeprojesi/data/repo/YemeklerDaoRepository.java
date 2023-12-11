package com.barisbalcikoca.bitirmeprojesi.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.barisbalcikoca.bitirmeprojesi.data.entity.CRUDCevap;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.SepetYemeklerCevap;
import com.barisbalcikoca.bitirmeprojesi.data.entity.Yemekler;
import com.barisbalcikoca.bitirmeprojesi.data.entity.YemeklerCevap;
import com.barisbalcikoca.bitirmeprojesi.retrofit.YemeklerDao;
import com.barisbalcikoca.bitirmeprojesi.ui.adapter.SepetAdapter;

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

    public void yemekleriYukle(){//ANASAYFADA TÜM YEMEKLERİ GETİRMEK İÇİN KULLANILIYOR
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

    //DETAY SAYFADA EN SON SEPETE YEMEK EKLEMEK İÇİN KULLANILIYOr.
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



    //SEPETE ULAŞAN O KULLANICIYA AİT SEPETTEKEİ YEMEKLERİ GETİRMEK İÇİN KULLANILIYOR
    //SEPETYEMEKLERCEVAP SINIFI
    public void sepettekiYemekleriGetir(String kullanici_adi){
        ydao.sepettekiYemekleriGetir(kullanici_adi).enqueue(new Callback<SepetYemeklerCevap>() {

            @Override
            public void onResponse(Call<SepetYemeklerCevap> call, Response<SepetYemeklerCevap> response) {
                List<SepetYemekler> sepetYemekler = response.body().getSepet_yemekler();
                sepetYemeklerListesi.setValue(sepetYemekler);
            }
            @Override
            public void onFailure(Call<SepetYemeklerCevap> call, Throwable t) {
            }
        });
    }

    //SEPETTE VAR OLAN YEMEĞİ SİLMEK İÇİN KULLANILIYOR.
    public void sepettenYemekSil(int sepet_yemek_id,String kullanici_adi){
        ydao.sepettenYemekSil(sepet_yemek_id,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepettekiYemekleriGetir(kullanici_adi);

            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }


}
