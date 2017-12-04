package cn.net.sinodata.ddj.app;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Ivy on 2017/11/13.
 */

public class MyApplication extends Application {

    private static MyApplication mContext;
    private static Handler handler;
    private static int mainTid;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        handler = new Handler();
        mainTid = android.os.Process.myTid();

    }


    public static MyApplication getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainTid() {
        return mainTid;
    }

}
