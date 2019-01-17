package com.thiennguyen.filmseeker.di;

import com.thiennguyen.data.network.service.ApiServiceImpl;
import com.thiennguyen.data.network.service.HttpMapService;
import com.thiennguyen.data.network.service.HttpMovieService;
import com.thiennguyen.domain.service.ApiService;
import com.thiennguyen.filmseeker.Constant;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thien.nguyen on 10/11/18.
 */

@Module
public class NetworkModule {

  private final String API_GOOGLE_MAP_KEY;

  private final String API_MOVIE_KEY;

  public NetworkModule(String API_GOOGLE_MAP_KEY, String API_MOVIE_KEY) {
    this.API_GOOGLE_MAP_KEY = API_GOOGLE_MAP_KEY;
    this.API_MOVIE_KEY = API_MOVIE_KEY;
  }

  @Provides
  @Singleton
  public ApiService provideApiService(HttpMapService httpMapService, HttpMovieService httpMovieService) {
    return new ApiServiceImpl(httpMapService, httpMovieService);
  }

  @Provides
  @Singleton
  public GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create();
  }

  @Provides
  @Singleton
  public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Provides
  @Singleton
  public HttpMapService provideMapService(@Named("map") Retrofit retrofit) {
    return retrofit.create(HttpMapService.class);
  }

  @Provides
  @Singleton
  public HttpMovieService provideHttpMovieService(@Named("movie") Retrofit retrofit) {
    return retrofit.create(HttpMovieService.class);
  }

  @Provides
  @Singleton
  @Named("map")
  public OkHttpClient provideOkHttpClientMap(HttpLoggingInterceptor httpLoggingInterceptor) {
    return new OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .addInterceptor(chain -> {
          Request original = chain.request();
          HttpUrl originalHttpUrl = original.url();

          HttpUrl url = originalHttpUrl.newBuilder()
              .addQueryParameter(Constant.Map.API_KEY_NAME, API_GOOGLE_MAP_KEY)
              .build();

          //Request customization: add request headers
          Request.Builder requestBuilder = original.newBuilder()
              .url(url);

          return chain.proceed(requestBuilder.build());
        })
        .readTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .build();
  }

  @Provides
  @Singleton
  @Named("movie")
  public OkHttpClient provideOkHttpClientMovie(HttpLoggingInterceptor httpLoggingInterceptor) {
    return new OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .addInterceptor(chain -> {
          Request original = chain.request();
          HttpUrl originalHttpUrl = original.url();

          HttpUrl url = originalHttpUrl.newBuilder()
              .addQueryParameter(Constant.Movie.API_KEY_NAME, API_MOVIE_KEY)
              .build();

          //Request customization: add request headers
          Request.Builder requestBuilder = original.newBuilder()
              .url(url);

          return chain.proceed(requestBuilder.build());
        })
        .readTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(Constant.Network.TIME_OUT, TimeUnit.SECONDS)
        .build();
  }

  @Provides
  @Singleton
  @Named("map")
  public Retrofit provideRetrofitMap(GsonConverterFactory gsonConverterFactory, @Named("map") OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(Constant.Map.MAP_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build();
  }

  @Provides
  @Singleton
  @Named("movie")
  public Retrofit provideRetrofitMovie(GsonConverterFactory gsonConverterFactory, @Named("movie") OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(Constant.Movie.MOVIE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build();
  }
}
