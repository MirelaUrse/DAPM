package com.example.oana.studnote;
import android.app.Activity;
//import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.oana.studnote.database.Courses;
import com.example.oana.studnote.database.DataSource;
import com.example.oana.studnote.database.Timetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Oana on 1/9/2015.
 */

public class TimetableFragment extends Fragment {
    private static final String TAG = "TimetableFragment";
    static final int TIME_DIALOG_ID = 999;

    private ImageButton backButton;
    private ImageButton addButton;
    private Button addConfirmButton;
    private Button changeHourButton;

    private LinearLayout newEvent;
    private EditText editTextEvent;
    private TextView txtHour;
    private TimePicker timePicker;
    private Spinner spinnerDays;
    private int hour;
    private int minute;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<TimetableListItem>> listDataChild;

    DataSource dataSource;

    ArrayAdapter<CharSequence> spinnerAdapter;

    private Fragment homeFragment;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.timetable_fragment, container, false);
        /*if(savedInstanceState!=null){
            Parcelable listState = savedInstanceState.getParcelable("ListState");
            //expListView.setAdapter(new android.widget.ExpandableListAdapter());
        }*/

        backButton = (ImageButton) view.findViewById(R.id.back_button);
        addButton = (ImageButton) view.findViewById(R.id.add_button);
        addConfirmButton = (Button)view.findViewById(R.id.btn_add);
        changeHourButton = (Button)view.findViewById(R.id.btn_change_hour);
        editTextEvent = (EditText) view.findViewById(R.id.txt_event);
        txtHour = (TextView) view.findViewById(R.id.txt_hour);
        timePicker = (TimePicker) view.findViewById(R.id.time_picker_timetable);

        spinnerDays = (Spinner) view.findViewById(R.id.spinner_days);
        // Create an ArrayAdapter using the string array and a default spinner layout
        spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDays.setAdapter(spinnerAdapter);

        Log.i(TAG, "Error in on view create");
        newEvent = (LinearLayout) view.findViewById(R.id.new_event_layout);
        expListView = (ExpandableListView)view.findViewById(R.id.expandable_timetable);
        //prepare data
        MainActivity mainActivity=(MainActivity)getActivity();
        dataSource=mainActivity.getDataSource();
        dataSource.open();



        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<TimetableListItem>>();
        setItemsOnView();

        for(String header:listDataHeader)
        {
            ArrayList<TimetableListItem> timetableListItems =new ArrayList<TimetableListItem>();
            List<Timetable> timetableList = dataSource.getDailyTimetable(header);
            for(Timetable timetable:timetableList)
            {
                TimetableListItem item = new TimetableListItem(timetable,0);
                timetableListItems.add(item);
            }
            listDataChild.put(header,timetableListItems);
        }
        dataSource.close();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHomeOnClick();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewEventLayoutOnClick();
            }
        });
        addConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewEventOnClick();
            }
        });
        changeHourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeHourOnClick();
            }
        });

        return view;
    }

    private void showNewEventLayoutOnClick(){
        //set the layout visible for adding a courses
        //spinnerDays.getSelectedItemPosition();
        spinnerDays.setAdapter(spinnerAdapter);
        editTextEvent.setText("");
        setCurrentTimeOnView();
        newEvent.setVisibility(View.VISIBLE);

    }
    private void addNewEventOnClick(){
        dataSource.open();
        dataSource.createTimetable(spinnerDays.getSelectedItem().toString(),editTextEvent.getText().toString(),txtHour.getText().toString());
        dataSource.close();
        //add new events to the expandable list
        TimetableListItem event = new TimetableListItem(spinnerDays.getSelectedItem().toString(),txtHour.getText().toString(),editTextEvent.getText().toString(),0);
        //listDataHeader.add(editTextCourses.getText().toString());
        String key = spinnerDays.getSelectedItem().toString();
        List<TimetableListItem> eventsList =new ArrayList<>();
        eventsList = listDataChild.get(key);
        eventsList.add(event);
        listDataChild.put(key,eventsList);
        //listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        newEvent.setVisibility(View.GONE);
    }

    public void setCurrentTimeOnView() {

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        String h = "";
        String min = "";
        if(hour<10)
        {
            h="0"+hour;
        }
        else
        {
            h=""+hour;
        }
        if(minute<10)
        {
            min="0"+minute;
        }
        else
        {
            min=""+minute;
        }


        // set current time into textview
        txtHour.setText(
                new StringBuilder().append(h)
                        .append(":").append(min));

        // set current time into timepicker
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

    }

    public void setItemsOnView() {

        listDataHeader.add("Monday");
        listDataChild.put("Monday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Tuesday");
        listDataChild.put("Tuesday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Wednesday");
        listDataChild.put("Wednesday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Thursday");
        listDataChild.put("Thursday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Friday");
        listDataChild.put("Friday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Saturday");
        listDataChild.put("Saturday",new ArrayList<TimetableListItem>());
        listDataHeader.add("Sunday");
        listDataChild.put("Sunday",new ArrayList<TimetableListItem>());
    }

    public void changeHourOnClick() {

        DialogFragment dialogFragment = new DialogTimePickerFragment();
        dialogFragment.setTargetFragment(this, 1);
        dialogFragment.show(getFragmentManager(), "dialog");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Stuff to do, dependent on requestCode and resultCode
        if(requestCode == 1)  // 1 is an arbitrary number, can be any int
        {
            // This is the return result of your DialogFragment
            if(resultCode == 1) // 1 is an arbitrary number, can be any int
            {


            }
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*OnHourChangedListener mCallback;

    // Container Activity must implement this interface
    public interface OnHourChangedListener {
        public void onHourChanged(int position);
    }

    @Overridet throws an exception
        try {
            mCallback = (OnHourChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHourChangedListener");
        }
    }*/
    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, i
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