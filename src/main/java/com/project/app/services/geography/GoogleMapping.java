package com.project.app.services.geography;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleMapping {

    final static Logger logger = LoggerFactory.getLogger(GoogleMapping.class);

    private final String API_KEY = "AIzaSyCYAz-FGzS32ayo9nFB1qvoqYBG2V3DjxE";

    // GeoApiContext geoContext = new GeoApiContext().setApiKey(API_KEY);
    //
    // GeocodingResult[] results = GeocodingApi.geocode(geoContext,
    // "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
    // System.out.println(results[0].formattedAddress);
    //
    // private LatLng latlon = new LatLng(41.0963, 16.8629);
    // GeocodingApiRequest req = new GeocodingApiRequest(geoContext).latlng(latlon);
    //
    //

}
