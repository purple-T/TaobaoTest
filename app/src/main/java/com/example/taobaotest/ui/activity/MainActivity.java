package com.example.taobaotest.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.taobaotest.R;
import com.example.taobaotest.base.BaseFragment;
import com.example.taobaotest.ui.fragment.HomeFragment;
import com.example.taobaotest.ui.fragment.RedPacketFragment;
import com.example.taobaotest.ui.fragment.SearchFragment;
import com.example.taobaotest.ui.fragment.SelectedFragment;
import com.example.taobaotest.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.main_navigaion_bar) BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private SelectedFragment mSelectedFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFm;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);

        initFragment();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind!=null) {
            mBind.unbind();
        }

    }

    private void initFragment() {

        mHomeFragment = new HomeFragment();
        mSelectedFragment = new SelectedFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();

        mFm = getSupportFragmentManager();

        replaceFragment(mHomeFragment);


    }

    private void replaceFragment(BaseFragment fragment) {

        FragmentTransaction transaction = mFm.beginTransaction();

        transaction.replace(R.id.main_page_container,fragment);
        transaction.commit();

    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {

            LogUtils.d(this,"点击了"+item.getTitle() + "id---"+item.getItemId());

            switch (item.getItemId()){
                case R.id.menu_home:{
                    replaceFragment(mHomeFragment);
                }
                break;
                case R.id.menu_selected:{
                    replaceFragment(mSelectedFragment);

                }
                break;
                case R.id.menu_red_packet:{
                    replaceFragment(mRedPacketFragment);

                }
                break;
                case R.id.menu_search:{
                    replaceFragment(mSearchFragment);

                }
                break;
                default:
                    break;
            }


            return true;
        });

    }


}
