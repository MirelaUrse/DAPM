package com.example.lavy.myapplication;

import android.app.Activity;
//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private static final int HOME = 0;//the fragment´s number for the HomeFragment
    private static final int AGENDA = 1;//the fragment´s number for the AgendaFragment
    //private static final int SETTINGS = 2;//the fragment´s number for the SettingsFragment
    private static final int FRAGMENT_COUNT = AGENDA + 1;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];//array of fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        fragments[HOME]=fm.findFragmentById(R.id.home);
        fragments[AGENDA]=fm.findFragmentById(R.id.agenda);
        FragmentTransaction ft = fm.beginTransaction();
        //hide the fragments initially
        for (int i=0;i<fragments.length;i++){
            ft.hide(fragments[i]);
        }
        showFragment(HOME,true);
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**Show a given fragment and hide all others fragments
     * @param fragmentIndex the fragment'index in the array of fragments
     * @param addToBackStack
     */
    private void showFragment(int fragmentIndex, boolean addToBackStack){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (int i=0;i<fragments.length;i++){
            if(i == fragmentIndex){
                ft.show(fragments[i]);
            }else {
                ft.hide(fragments[i]);
            }
        }
        if (addToBackStack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
