package com.example.oana.studnote;
import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Oana on 1/9/2015.
 */

public class CoursesFragment extends Fragment {
    private static final String TAG = "CoursesFragment";
    private ImageButton backButton;
    private ImageButton addButton;
    private Button addConfirmButton;

    private LinearLayout newCourses;
    private EditText editTextCourses;
    private EditText editTextTeacher;
    private EditText editTextWhen;
    private EditText editTextWhere;
    private EditText editTextGrading;
    private EditText editTextOther;

    ExpandableListAdapter listAdapter;
    ListView listView;
    List<String> listDataHeader;
    List<CoursesListElement> listElements;

    private Fragment homeFragment;


    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.courses_fragment, container, false);

        if(savedInstanceState!=null){
            Parcelable listState = savedInstanceState.getParcelable("ListState");
            //expListView.setAdapter(new android.widget.ExpandableListAdapter());
        }

        backButton = (ImageButton) view.findViewById(R.id.back_button);
        addButton = (ImageButton) view.findViewById(R.id.add_button);
        addConfirmButton = (Button)view.findViewById(R.id.btn_add);


        editTextCourses = (EditText) view.findViewById(R.id.txt_courses);
        editTextTeacher = (EditText) view.findViewById(R.id.txt_teacher);
        editTextWhen = (EditText) view.findViewById(R.id.txt_when);
        editTextWhere = (EditText) view.findViewById(R.id.txt_where);
        editTextGrading = (EditText) view.findViewById(R.id.txt_grading);
        editTextOther = (EditText) view.findViewById(R.id.txt_other);
        Log.i(TAG,"Error in on view create");
        newCourses = (LinearLayout) view.findViewById(R.id.new_courses_layout);

        listView = (ListView) view.findViewById(R.id.list_courses);
        // Set up the list view items, based on a list of
        // BaseListElement items
        listElements = new ArrayList<CoursesListElement>();
        listView.setAdapter(new ActionListAdapter(getActivity(),R.id.list_courses, listElements));

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
        addConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewCoursesOnClick();
            }
        });







        return view;
    }

    /*
     * Preparing the list data
     */


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
    private void addNewCoursesOnClick(){
        //add new courses to the expandable list
        CoursesListElement courses = new CoursesListElement(editTextCourses.getText().toString(),editTextTeacher.getText().toString(),editTextWhen.getText().toString(),editTextWhere.getText().toString(),editTextGrading.getText().toString(),editTextOther.getText().toString(),0);

        listElements.add(courses);
        // setting list adapter
        listView.setAdapter(new ActionListAdapter(getActivity(),R.id.list_courses, listElements));
        newCourses.setVisibility(View.GONE);

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

    /*
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Parcelable listState = savedInstanceState.getParcelable("ListState");
        listView.onRestoreInstanceState(listState);
    }*/

    private class ActionListAdapter extends ArrayAdapter<CoursesListElement> {
        private List<CoursesListElement> listElements;

        public ActionListAdapter(Context context, int resourceId, List<CoursesListElement> listElements) {
            super(context, resourceId, listElements);
            this.listElements = listElements;
            // Set up as an observer for list item changes to
            // refresh the view.
            for (int i = 0; i < listElements.size(); i++) {
                listElements.get(i).setAdapter(this);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater =
                        (LayoutInflater) getActivity()
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.courses_list_item, null);
            }

            CoursesListElement listElement = listElements.get(position);
            if (listElement != null) {
                //view.setOnClickListener(listElement.getOnClickListener());
                TextView txtCourses = (TextView) view.findViewById(R.id.txt_courses);
                TextView txtTeacher = (TextView) view.findViewById(R.id.txt_teacher);
                TextView txtWhen = (TextView) view.findViewById(R.id.txt_when);
                TextView txtWhere = (TextView) view.findViewById(R.id.txt_where);
                TextView txtGrading = (TextView) view.findViewById(R.id.txt_grading);
                TextView txtOther = (TextView) view.findViewById(R.id.txt_other);

                txtCourses.setText(listElement.getCourses());
                txtTeacher.setText(listElement.getTeacher());
                txtWhen.setText(listElement.getWhen());
                txtWhere.setText(listElement.getWhere());
                txtGrading.setText(listElement.getGrading());
                txtOther.setText(listElement.getOther());

            }
            return view;
        }


    }
}