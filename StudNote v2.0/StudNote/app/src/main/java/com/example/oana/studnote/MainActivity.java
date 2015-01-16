package com.example.oana.studnote;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    //constants used to manipulate the fragments
    private static final int HOME = 0;
    private static final int AGENDA = 1;
    private static final int SETTINGS = 2;//the fragment´s number for the UserSettingsFragment
    private static final int FRAGMENT_COUNT =2;
    private Fragment[] fragments = new Fragment[FRAGMENT_COUNT];//array of fragments
    private boolean isResumed = false;//flag that enables session state change checks

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HomeFragment homeFragment = new HomeFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            homeFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment).commit();
        }
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
}

/*public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}*/
