package simbulan.androidtabs.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import simbulan.androidtabs.R;
import simbulan.androidtabs.fragment.ListFragment;
import simbulan.androidtabs.fragment.MapFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to a tab
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // Instantiates the  fragment for the given tab
        switch (position) {
            case 0:
                return new MapFragment();
            case 1:
                return new ListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Returns the number of tabs
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Returns the title for the given tab
        Resources resources = context.getResources();
        switch (position) {
            case 0:
                return resources.getString(R.string.tab_map_title);
            case 1:
                return resources.getString(R.string.tab_list_title);
        }
        return null;
    }
}