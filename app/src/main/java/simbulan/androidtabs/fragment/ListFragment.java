package simbulan.androidtabs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import simbulan.androidtabs.R;
import simbulan.androidtabs.adapter.ExpandableListAdapter;
import simbulan.androidtabs.model.Capital;
import simbulan.androidtabs.util.CapitalsData;

/**
 * A {@link Fragment} that displays a list view of the provincial / territorial capitals of Canada
 */
public class ListFragment extends Fragment {
    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listDataHeaders;
    HashMap<String, List<Capital>> listDataItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        prepareListData();

        listView = (ExpandableListView) rootView.findViewById(R.id.expandableListView);
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeaders, listDataItems);
        listView.setAdapter(listAdapter);
        listView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        "Clicked: " + listDataHeaders.get(groupPosition),
//                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        listView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Expanded: "+ listDataHeaders.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
            }

        });

        listView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Collapsed: " + listDataHeaders.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
            }

        });

        listView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String header = listDataHeaders.get(groupPosition);
                Toast.makeText(getActivity().getApplicationContext(),
                        header + " : " + listDataItems.get(header).get(childPosition).getProvince(),
                        Toast.LENGTH_SHORT)
                        .show();
                return false;
            }

        });

        return rootView;
    }

    private void prepareListData() {
        CapitalsData data = CapitalsData.getInstance();
        listDataHeaders = data.getRegions();
        listDataItems = data.getCapitals();
    }
}
