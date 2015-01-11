package com.example.oana.studnote;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Oana on 1/9/2015.
 */

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Button timetableButton;
    private Button coursesButton;
    private Button agendaButton;
    private Button marksButton;
    private Button settingsButton;
    private Fragment agendaFragment;
    private Fragment timetableFragment;
    private Fragment coursesFragment;
    private Fragment marksFragment;
    private Fragment settingsFragment;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);



    timetableButton = (Button) view.findViewById(R.id.buttonTimetable);
    coursesButton = (Button) view.findViewById(R.id.buttonCourses);
    agendaButton = (Button) view.findViewById(R.id.buttonAgenda);
    marksButton = (Button) view.findViewById(R.id.buttonMarks);
    settingsButton = (Button) view.findViewById(R.id.buttonSetings);
    timetableButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showTimetableOnClick();
        }
    });
    coursesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showCoursesOnClick();
        }
    });
    agendaButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAgendaOnClick();
        }
    });
    marksButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showMarksOnClick();
        }
    });
    settingsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showSettingsOnClick();
        }
    });


    return view;
}

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    public void onFragmentInteraction(Uri uri);
}

    /**
     * Show the fragment containing the calendar
     */
    private void showAgendaOnClick(){
        //create a new fragment and transaction
        agendaFragment = new AgendaFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,agendaFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Show the fragment containing the timetable
     */
    private void showTimetableOnClick(){
        //create a new fragment and transaction
        timetableFragment = new TimetableFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,timetableFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Show the fragment containing the courses
     */
    private void showCoursesOnClick(){
        //create a new fragment and transaction
        coursesFragment = new CoursesFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,coursesFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Show the fragment containing the timetable
     */
    private void showMarksOnClick(){
        //create a new fragment and transaction
        marksFragment = new MarksFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,marksFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * Show the fragment containing the settings
     */
    private void showSettingsOnClick(){
        //create a new fragment and transaction
        settingsFragment = new SettingsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,settingsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }








    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}