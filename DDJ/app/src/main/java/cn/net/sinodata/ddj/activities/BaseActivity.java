package cn.net.sinodata.ddj.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Ivy on 2017/12/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        ButterKnife.bind(this);
        initView();
    }



    /**
     * @return 返回加载布局文件
     */
    protected abstract int getLayoutView();


    /**
     * 初始化页面数据
     */
    protected abstract void initView();
}
