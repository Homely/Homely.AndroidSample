package au.com.homely.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    FavouritesManager favouritesManager;
    private static ListingsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.list);
        this.favouritesManager = new FavouritesManager(new ArrayList<Integer>());

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void run() throws IOException {
        ListingSearchClient client = new ListingSearchClient();

        final Integer locationId = 6218477;
        client.searchListings(locationId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String jsonString = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListingSearchParser parser = new ListingSearchParser();
                        ArrayList<ListingModel> listings = parser.parseJSONResponse(jsonString);
                        MainActivity.this.adapter = new ListingsListAdapter(listings, getApplicationContext(), MainActivity.this.favouritesManager);
                        MainActivity.this.listView.setAdapter(MainActivity.this.adapter);
                    }
                });
            }
        });
    }

}
