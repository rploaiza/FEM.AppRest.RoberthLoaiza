package es.upm.alumnos.femapprestroberthloaiza.database.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Usuario on 27/10/2017.
 */

public class ResultParce implements Parcelable {

    private Integer id;
    private Integer licorsId;
    private String name;
    private String tags;
    private String priceInCents;
    private String primaryCategory;
    private String origin;
    private String packageUnitVolumeInMilliliters;
    private String alcoholContent;
    private String producerName;
    private String imageThumbUrl;
    private String varietal;
    private String style;

    public ResultParce(int id, int licorsId, String name, String tags, String priceInCents, String primaryCategory,
                       String origin, String packageUnitVolumeInMilliliters, String alcoholContent,
                       String producerName, String imageThumbUrl, String varietal, String style) {

        this.setId(id);
        this.setLicorsId(licorsId);
        this.setName(name);
        this.setTags(tags);
        this.setPriceInCents(priceInCents);
        this.setPrimaryCategory(primaryCategory);
        this.setOrigin(origin);
        this.setPackageUnitVolumeInMilliliters(packageUnitVolumeInMilliliters);
        this.setAlcoholContent(alcoholContent);
        this.setProducerName(producerName);
        this.setImageThumbUrl(imageThumbUrl);
        this.setVarietal(varietal);
        this.setStyle(style);
    }

    protected ResultParce(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.tags = in.readString();
        this.priceInCents = in.readString();
        this.primaryCategory = in.readString();
        this.origin = in.readString();
        this.packageUnitVolumeInMilliliters = in.readString();
        this.alcoholContent = in.readString();
        this.producerName = in.readString();
        this.imageThumbUrl = in.readString();
        this.varietal = in.readString();
        this.style = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLicorsId() {
        return licorsId;
    }

    public void setLicorsId(Integer licorsId) {
        this.licorsId = licorsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(String priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(String packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", name='" + name +
                ", tags='" + tags +
                ", priceInCents=" + priceInCents +
                ", primaryCategory='" + primaryCategory +
                ", origin='" + origin +
                ", packageUnitVolumeInMilliliters=" + packageUnitVolumeInMilliliters +
                ", alcoholContent=" + alcoholContent +
                ", producerName='" + producerName +
                ", imageThumbUrl='" + imageThumbUrl +
                ", varietal='" + varietal +
                ", style='" + style +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResultParce> CREATOR = new Creator<ResultParce>() {
        @Override
        public ResultParce createFromParcel(Parcel in) {
            return new ResultParce(in);
        }

        @Override
        public ResultParce[] newArray(int size) {
            return new ResultParce[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.tags);
        dest.writeValue(this.priceInCents);
        dest.writeString(this.primaryCategory);
        dest.writeString(this.origin);
        dest.writeValue(this.packageUnitVolumeInMilliliters);
        dest.writeValue(this.alcoholContent);
        dest.writeString(this.producerName);
        dest.writeString(this.imageThumbUrl);
        dest.writeString(this.varietal);
        dest.writeString(this.style);
    }
}
