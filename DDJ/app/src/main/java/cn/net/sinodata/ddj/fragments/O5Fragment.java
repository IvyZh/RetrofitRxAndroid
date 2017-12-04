package cn.net.sinodata.ddj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.net.sinodata.ddj.R;
import cn.net.sinodata.ddj.domain.douban.Book;
import cn.net.sinodata.ddj.net.Retrofit2Utils;
import cn.net.sinodata.ddj.net.ServiceApi;
import cn.net.sinodata.ddj.utils.L;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivy on 2017/12/4.
 */

public class O5Fragment extends BaseFragment {
    @BindView(R.id.bt_load)
    Button mBtLoad;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    Unbinder unbinder;
    @BindView(R.id.bt_load2)
    Button mBtLoad2;
    @BindView(R.id.bt_load3)
    Button mBtLoad3;

    ServiceApi api;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.o5_fragment, container, false);
    }

    @Override
    public void initView() {

        api = Retrofit2Utils.getRetrofit().create(ServiceApi.class);

    }


    @OnClick(R.id.bt_load)
    public void onViewClicked() {

    }


    @OnClick({R.id.bt_load, R.id.bt_load2, R.id.bt_load3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_load:
                callBean();
                break;
            case R.id.bt_load2:
                callString();
                break;
            case R.id.bt_load3:
                callRxJava();
                break;
        }
    }

    private void callRxJava() {
        api.getBookInfoRxJava(27154489)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        L.v("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Book book) {
                        L.v("onNext:" + book);
                        setTextView(book.getTitle());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.v("onError:" + e.getLocalizedMessage());

                    }

                    @Override
                    public void onComplete() {
                        L.v("onComplete");

                    }
                });

//        Observable.create(new ObservableOnSubscribe<Book>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Book> e) throws Exception {
////                e.onNext();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Book>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Book book) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    private void callString() {
        api.getBookInfoString(27130613).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                setTextView(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void callBean() {
        api.getBookInfo(27138973).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
//                L.v("success:" + response.body().getTitle());
                setTextView(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                L.v("fail:" + t.getLocalizedMessage());
            }
        });
    }

    private void setTextView(String title) {
        mTvContent.setText(title);
        L.v("content:" + title);
    }
}
