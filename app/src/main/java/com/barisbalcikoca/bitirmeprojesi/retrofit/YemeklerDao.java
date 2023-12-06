package com.barisbalcikoca.bitirmeprojesi.retrofit;

import com.barisbalcikoca.bitirmeprojesi.data.entity.CRUDCevap;
import com.barisbalcikoca.bitirmeprojesi.data.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemeklerDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //BASE URL                  : http://kasimadalan.pe.hu
    //WEB SERVİS TEMSİL EDER    : yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    Call<YemeklerCevap> yemekerliYukle();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepeteYemekEkle(@Field("yemek_adi") String yemek_adi,
                           @Field("yemek_resim_adi") String yemek_resim_adi,
                           @Field("yemek_fiyat") int yemek_fiyat,
                           @Field("yemek_siparis_adet") int yemek_siparis_adet,
                           @Field("kullanici_adi") String kullanici_adi
                           );

}
