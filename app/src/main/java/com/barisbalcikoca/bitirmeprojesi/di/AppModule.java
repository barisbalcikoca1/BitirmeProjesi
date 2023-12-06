package com.barisbalcikoca.bitirmeprojesi.di;

import com.barisbalcikoca.bitirmeprojesi.data.repo.YemeklerDaoRepository;
import com.barisbalcikoca.bitirmeprojesi.retrofit.ApiUtils;
import com.barisbalcikoca.bitirmeprojesi.retrofit.YemeklerDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton

    public YemeklerDaoRepository provideYemeklerDaoRepository(YemeklerDao ydao){
        return new YemeklerDaoRepository(ydao);
    }

    @Provides
    @Singleton

    public YemeklerDao provideYemeklerDao(){
        return ApiUtils.getYemeklerDao();
    }
}
