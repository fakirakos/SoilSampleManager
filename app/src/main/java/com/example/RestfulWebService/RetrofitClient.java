package com.example.RestfulWebService;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.1.2:8080/api/";  //192.168.1.4
    private static Retrofit retrofit=null;

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    public static ApiEndpointService getUserService(){
        ApiEndpointService apiEndpointService = getRetrofit().create(ApiEndpointService.class);

        return apiEndpointService;
    }

    //based on video https://www.youtube.com/watch?v=7aRn2Ch7Cs0 stopped at 8:58
}
