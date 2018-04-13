package au.com.homely.sampleapp;

import java.net.URL;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ListingSearchClient {

    public void searchListings(Integer locationId, Callback responseCallback) {
        String url = "https://api.homely.com.au/listings/location/list?json={\"sort\":5,\"filter\":{\"mode\":1,\"locationId\":" +locationId + "},\"paging\":{\"skip\":0,\"take\":50}}";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(responseCallback);
    }

}
