package com.example.taobaotest.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.taobaotest.R;
import com.example.taobaotest.ui.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Log.d(TAG,"点击了"+item.getTitle() + "id---"+item.getItemId());

                switch (item.getItemId()){
                    case R.id.menu_home:{

                    }
                    break;
                    case R.id.menu_selected:{

                    }
                    break;
                    case R.id.menu_red_packet:{

                    }
                    break;
                    case R.id.menu_search:{

                    }
                    break;
                    default:
                        break;
                }


                return true;
            }
        });

    }

    private void initView() {


        mNavigationView = findViewById(R.id.main_navigaion_bar);



        HomeFragment fragment = new HomeFragment();

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.add(R.id.main_page_container,fragment);
        fragmentTransaction.commit();

    }
}
