package au.com.homely.sampleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class ListingsListAdapter
        extends ArrayAdapter<ListingModel>
        implements View.OnClickListener
{

    private ArrayList<ListingModel> models;
    Context mContext;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    FavouritesManager manager;

    // View lookup cache
    private static class ViewHolder {
        NetworkImageView thumbnail;
        TextView statuses;
        TextView priceDescription;
        TextView address;
        TextView bedBathCar;
        ImageView heartButton;
    }

    public ListingsListAdapter(ArrayList<ListingModel> models, Context context, FavouritesManager manager) {
        super(context, R.layout.listing_row_item, models);
        this.models = models;
        this.mContext = context;
        this.manager = manager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListingModel listingModel = this.models.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listing_row_item, parent, false);
            viewHolder.thumbnail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
            viewHolder.statuses = (TextView) convertView.findViewById(R.id.statuses);
            viewHolder.priceDescription = (TextView) convertView.findViewById(R.id.priceDescription);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.bedBathCar = (TextView) convertView.findViewById(R.id.bedBathCar);
            viewHolder.heartButton = (ImageView) convertView.findViewById(R.id.heart_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        viewHolder.thumbnail.setImageUrl(listingModel.getImageUrl(), imageLoader);

        viewHolder.statuses.setText(listingModel.getStatuses());
        viewHolder.priceDescription.setText(listingModel.getPriceDescription());
        viewHolder.address.setText(listingModel.getAddressDescription());
        viewHolder.bedBathCar.setText(listingModel.getBedBathCar());
        viewHolder.heartButton.setTag(listingModel);
        viewHolder.heartButton.setOnClickListener(this);

        Integer listingId = listingModel.getId();
        this.updateHearButton(viewHolder.heartButton, listingId);

        return convertView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.heart_button:
                ImageView heartButton = (ImageView) v;
                ListingModel listingModel = (ListingModel) heartButton.getTag();
                Integer listingId = listingModel.getId();
                if (this.manager.isFavourite(listingId)) {
                    this.manager.remove(listingId);
                } else {
                    this.manager.add(listingId);
                }
                this.updateHearButton(heartButton, listingId);
                break;
        }
    }

    private void updateHearButton(ImageView heartButton, Integer listingId) {
        if (this.manager.isFavourite(listingId)) {
            heartButton.setImageResource(R.drawable.ic_heart_icon_full);
        } else {
            heartButton.setImageResource(R.drawable.ic_heart_icon);
        }
    }

}
