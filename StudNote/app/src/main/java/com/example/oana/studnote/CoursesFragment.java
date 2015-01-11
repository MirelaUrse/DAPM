package com.example.oana.studnote;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oana on 1/9/2015.
 */

public class CoursesFragment extends Fragment {
    private static final String TAG = "CoursesFragment";
    private ImageButton backButton;
    private ImageButton addButton;
    private LinearLayout newCourses;
    private ExpandableListView listView;

    private List<CoursesListElement> listElements;



    private Fragment homeFragment;


    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.courses_fragment, container, false);



        backButton = (ImageButton) view.findViewById(R.id.back_button);
        addButton = (ImageButton) view.findViewById(R.id.add_button);
        newCourses = (LinearLayout) view.findViewById(R.id.new_courses_layout);
        listView = (ExpandableListView)view.findViewById(R.id.expandable_courses);
        listElements = new ArrayList<CoursesListElement>();
        //listElements.setAdapter(new BaseExpandableListAdapter());
        //listView.setAdapter(new ListAdapter(getActivity(),R.id.expandable_courses, listElements));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHomeOnClick();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewCoursesLayoutOnClick();
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
    private void showHomeOnClick(){
        //create a new fragment and transaction
        homeFragment = new HomeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack
        ft.add(R.id.fragment_container,homeFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    private void showNewCoursesLayoutOnClick(){
        //set the layout visible for adding a courses
        newCourses.setVisibility(View.VISIBLE);

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