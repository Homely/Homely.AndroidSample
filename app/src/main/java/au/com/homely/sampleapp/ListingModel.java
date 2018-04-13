package au.com.homely.sampleapp;

public class ListingModel {

    Integer id;
    String imageIdentifier;
    LocationModel location;
    MainFeaturesModel mainFeatures;
    InfoModel info;

    public ListingModel(Integer id, String imageIdentifier, String address, String suburb, Integer bedrooms, Integer bathrooms, Integer carSpaces, String priceDescription, String statuses) {
        this.id = id;
        this.imageIdentifier = imageIdentifier;
        this.location = new LocationModel(address, suburb);
        this.mainFeatures = new MainFeaturesModel(bedrooms, bathrooms, carSpaces);
        this.info = new InfoModel(priceDescription, statuses);
    }

    public Integer getId() { return this.id; }

    public String getImageUrl() {
        return "https://res-5.cloudinary.com/hd1n2hd4y/image/upload/f_auto,fl_lossy,q_auto,c_fill,h_250,dpr_2.0/" + this.imageIdentifier + ".jpg";
    }

    public String getAddressDescription() { return this.location.getAddress() + ", " + this.location.getSuburb(); }
    public String getBedBathCar() { return this.mainFeatures.getBedrooms().toString() + " bed\t" + this.mainFeatures.getBathrooms().toString() + " bath\t" + this.mainFeatures.getCarSpaces().toString() + " car"; }
    public String getPriceDescription() { return this.info.getPriceDescription(); }
    public String getStatuses() { return this.info.getStatuses(); }

}


class LocationModel {
    String address;
    String suburb;

    public LocationModel(String address, String suburb) {
        this.address = address;
        this.suburb = suburb;
    }

    public String getAddress() { return this.address; }
    public String getSuburb() { return this.suburb; }

}


class MainFeaturesModel {
    Integer bedrooms;
    Integer bathrooms;
    Integer carSpaces;

    public MainFeaturesModel(Integer bedrooms, Integer bathrooms, Integer carSpaces) {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.carSpaces = carSpaces;
    }

    public Integer getBedrooms() { return this.bedrooms; }
    public Integer getBathrooms() { return this.bathrooms; }
    public Integer getCarSpaces() { return this.carSpaces; }

}


class InfoModel {
    String priceDescription;
    String statuses;

    public InfoModel(String priceDescription, String statuses) {
        this.priceDescription = priceDescription;
        this.statuses = statuses;
    }

    public String getPriceDescription() { return this.priceDescription; }
    public String getStatuses() { return this.statuses; }

}
