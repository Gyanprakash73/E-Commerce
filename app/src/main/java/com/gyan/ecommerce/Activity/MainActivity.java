package com.gyan.ecommerce.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyan.ecommerce.Fragment.AllPurposeFlourFragment;
import com.gyan.ecommerce.Fragment.AttaAndFloursFragment;
import com.gyan.ecommerce.Fragment.ShampooFragment;
import com.gyan.ecommerce.R;
import com.gyan.ecommerce.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Tab Layout
        setUpViewPager();
        binding.tabs.setupWithViewPager(binding.viewpager);

        //Bottom navigation Drawer
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.cart) {
                    startActivity(new Intent(getApplicationContext(),CartActivity.class));
                }
                return true;
            }
        });

    }

    private void setUpViewPager() {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AttaAndFloursFragment(),"Atta And Flours");
        adapter.addFragment(new ShampooFragment(),"Shampoo");
        adapter.addFragment(new AllPurposeFlourFragment(),"All Purpose Flour");
        binding.viewpager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList=new ArrayList<>();
        List<String> pageTitle=new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            pageTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position){
            return pageTitle.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}