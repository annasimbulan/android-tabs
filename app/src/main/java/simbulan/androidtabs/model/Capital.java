package simbulan.androidtabs.model;

import com.google.android.gms.maps.model.LatLng;

public class Capital {
    private String name;
    private String province;
    private LatLng coordinates;

    public Capital(String name, String province, LatLng coordinates) {
        this.name = name;
        this.province = province;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}
