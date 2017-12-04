package cn.net.sinodata.ddj.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivy on 2017/10/27.
 */

public abstract class Retrofit2Utils {
    private static Retrofit mRetrofit = null;

    private static String BASE_URL = "https://api.douban.com";

    private static OkHttpClient mOkHttpClient;

    public static Retrofit getRetrofit() {

        if (mRetrofit == null) {
            synchronized (Retrofit2Utils.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = OkHttp3Utils.getOkHttpClient();
                }
                if (mRetrofit == null) {
                    mRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(StringConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            // 添加Retrofit到RxJava的转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(mOkHttpClient)
                            .build();
                }
            }
        }

        return mRetrofit;

    }
}