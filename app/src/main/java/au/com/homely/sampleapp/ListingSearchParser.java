package au.com.homely.sampleapp;

import android.util.Log;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListingSearchParser {

    // Expected JSON format
    // https://api.homely.com.au/listings/ids/list?json=%7B%22filter%22%3A%7B%22listingIds%22%3A%5B4516319%2C4515183%2C4515686%2C4512828%2C4508864%2C4508579%2C4508320%2C4506751%2C4505434%2C4504978%2C4503041%2C4503022%2C4500675%2C4498497%2C4497713%2C4493943%2C4490576%2C4488080%2C4484727%2C4483904%2C4480933%2C4479000%2C4478776%2C4477552%2C4475096%2C4470191%2C4466383%2C4461847%2C4458577%2C4451639%2C4450951%2C4448578%2C4447411%2C4445797%2C4445230%2C4441769%2C4441076%2C4441075%2C4441073%2C4440969%2C4435159%2C4433927%2C4433646%2C4433179%2C4433019%2C4432793%2C4432053%2C4429723%2C4426958%2C4426379%5D%7D%7D
    public ArrayList<ListingModel> parseJSONResponse(String result) {
        ArrayList<ListingModel> itemsList = new ArrayList<ListingModel>();

        if (result != null) {
            try {
                JSONObject jObject = new JSONObject(result);

                JSONArray items = jObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject obj = items.getJSONObject(i);

                    Integer id = obj.getInt("id");

                    JSONArray images = obj.getJSONArray("images");
                    JSONObject image = images.getJSONObject(0);
                    String imageIdentifier = image.getString("identifier");

                    JSONObject location = obj.getJSONObject("location");
                    String address = location.getString("address");
                    String suburb = location.getString("suburb");

                    JSONObject mainFeatures = obj.getJSONObject("mainFeatures");
                    Integer bedrooms = mainFeatures.getInt("bedrooms");
                    Integer bathrooms = mainFeatures.getInt("bathrooms");
                    Integer carSpaces = mainFeatures.getInt("carspaces");

                    JSONObject info = obj.getJSONObject("info");
                    String priceDescription = info.getJSONObject("price").getString("longDescription");
                    JSONArray statusesArray = info.getJSONArray("statuses");
                    String statuses = statusesArray.getJSONObject(0).getString("value");

                    ListingModel listingModel = new ListingModel(id, imageIdentifier, address, suburb, bedrooms, bathrooms, carSpaces, priceDescription, statuses);
                    itemsList.add(listingModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // return item list
        return itemsList;
    }

}
