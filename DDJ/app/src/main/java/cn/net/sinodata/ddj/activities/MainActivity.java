package cn.net.sinodata.ddj.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.net.sinodata.ddj.R;
import cn.net.sinodata.ddj.domain.BottomTab;
import cn.net.sinodata.ddj.fragments.O1Fragment;
import cn.net.sinodata.ddj.fragments.O2Fragment;
import cn.net.sinodata.ddj.fragments.O3Fragment;
import cn.net.sinodata.ddj.fragments.O4Fragment;
import cn.net.sinodata.ddj.fragments.O5Fragment;
import cn.net.sinodata.ddj.ui.FragmentTabHost;

public class MainActivity extends BaseActivity {


    @BindView(R.id.realtabcontent)
    FrameLayout mRealtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout mTabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabhost;
    //用于装载每一个分页的Tab
    private List<BottomTab> mTabs = new ArrayList<>(5);
    private LayoutInflater mInflater;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(this);
        initTab();
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        BottomTab tab1 = new BottomTab(O1Fragment.class, R.string.tab1_name, R.drawable.selector_bottom_tab1);
        BottomTab tab2 = new BottomTab(O2Fragment.class, R.string.tab2_name, R.drawable.selector_bottom_tab2);
        BottomTab tab3 = new BottomTab(O3Fragment.class, R.string.tab3_name, R.drawable.selector_bottom_tab3);
        BottomTab tab4 = new BottomTab(O4Fragment.class, R.string.tab4_name, R.drawable.selector_bottom_tab4);
        BottomTab tab5 = new BottomTab(O5Fragment.class, R.string.tab5_name, R.drawable.selector_bottom_tab5);

        mTabs.add(tab1);
        mTabs.add(tab2);
        mTabs.add(tab3);
        mTabs.add(tab4);
        mTabs.add(tab5);


        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (BottomTab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }


        mTabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
            }
        });
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(4);
    }

    private View buildIndicator(BottomTab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }


}
