package simbulan.androidtabs.util;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import simbulan.androidtabs.model.Capital;

public class CapitalsData {
    private static final String REGION_ATLANTIC = "The Atlantic Provinces";
    private static final String REGION_CENTRAL = "Central Canada";
    private static final String REGION_PRAIRIE = "The Prairie Provinces";
    private static final String REGION_WEST_COAST = "The West Coast";
    private static final String REGION_NORTHERN_TERRITORIES = "The Northern Territories";

    private static CapitalsData INSTANCE;
    private static List<String> regions;
    private static HashMap<String, List<Capital>> capitals;

    private CapitalsData() {
        regions = new ArrayList<>();
        regions.add(REGION_ATLANTIC);
        regions.add(REGION_CENTRAL);
        regions.add(REGION_PRAIRIE);
        regions.add(REGION_WEST_COAST);
        regions.add(REGION_NORTHERN_TERRITORIES);

        final Capital stJohns = new Capital("St. John's", "Newfoundland and Labrador", new LatLng(47.558464, -52.720148));
        final Capital halifax = new Capital("Halifax", "Nova Scotia", new LatLng(44.649719, -63.574939));
        final Capital frederiction = new Capital("Fredericton", "New Brunswick", new LatLng(45.963125, -66.645264));
        final Capital charlottetown = new Capital("Charlottetown", "Prince Edward Island", new LatLng(46.239191, -63.130853));
        final Capital quebec = new Capital("Québec", "Quebec", new LatLng(46.804454, -71.242389));
        final Capital toronto = new Capital("Toronto", "Ontario", new LatLng(43.652231, -79.385259));
        final Capital winnipeg = new Capital("Winnipeg", "Manitoba", new LatLng(49.900284, -97.140552));
        final Capital regina = new Capital("Regina", "Saskatchewan", new LatLng(50.454660, -104.607600));
        final Capital edmonton = new Capital("Edmonton", "Alberta", new LatLng(53.549351, -113.489829));
        final Capital victoria = new Capital("Victoria", "British Columbia", new LatLng(48.428502, -123.365456));
        final Capital iqualit = new Capital("Iqaluit", "Nunavut", new LatLng(63.746315, -68.517501));
        final Capital yellowknife = new Capital("Yellowknife", "Northwest Territories", new LatLng(62.454243, -114.371229));
        final Capital whitehorse = new Capital("Whitehorse", "Yukon", new LatLng(60.721925, -135.057112));

        capitals = new HashMap<>();
        addCapitals(REGION_ATLANTIC, new Capital[]{stJohns, halifax, frederiction, charlottetown});
        addCapitals(REGION_CENTRAL, new Capital[]{quebec, toronto});
        addCapitals(REGION_PRAIRIE, new Capital[]{winnipeg, regina, edmonton});
        addCapitals(REGION_WEST_COAST, new Capital[]{victoria});
        addCapitals(REGION_NORTHERN_TERRITORIES, new Capital[]{iqualit, yellowknife, whitehorse});
    }

    public static CapitalsData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CapitalsData();
        }

        return INSTANCE;
    }

    public List<String> getRegions() {
        return regions;
    }

    public HashMap<String, List<Capital>> getCapitals() {
        return capitals;
    }

    private static void addCapitals(String region, Capital[] capitals) {
        ArrayList<Capital> capitalsInRegion = new ArrayList<>();
        for (Capital c : capitals) {
            capitalsInRegion.add(c);
        }
        CapitalsData.capitals.put(region, capitalsInRegion);
    }
}
