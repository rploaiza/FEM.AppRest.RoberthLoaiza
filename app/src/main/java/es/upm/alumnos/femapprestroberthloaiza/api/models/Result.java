package es.upm.alumnos.femapprestroberthloaiza.api.models;

public class Result {

    private Integer id;
    private String name;
    private String tags;
    private Integer price_in_cents;
    private String primary_category;
    private String origin;
    private Integer package_unit_volume_in_milliliters;
    private Integer alcohol_content;
    private String producer_name;
    private String image_thumb_url;
    private String varietal;
    private String style;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPriceInCents() {
        return price_in_cents;
    }

    public void setPriceInCents(Integer price_in_cents) {
        this.price_in_cents = price_in_cents;
    }

    public String getPrimaryCategory() {
        return primary_category;
    }

    public void setPrimaryCategory(String primary_category) {
        this.primary_category = primary_category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getPackageUnitVolumeInMilliliters() {
        return package_unit_volume_in_milliliters;
    }

    public void setPackageUnitVolumeInMilliliters(Integer package_unit_volume_in_milliliters) {
        this.package_unit_volume_in_milliliters = package_unit_volume_in_milliliters;
    }

    public Integer getAlcoholContent() {
        return alcohol_content;
    }

    public void setAlcoholContent(Integer alcohol_content) {
        this.alcohol_content = alcohol_content;
    }

    public String getProducerName() {
        return producer_name;
    }

    public void setProducerName(String producer_name) {
        this.producer_name = producer_name;
    }

    public String getImageThumbUrl() {
        return image_thumb_url;
    }

    public void setImageThumbUrl(String image_thumb_url) {
        this.image_thumb_url = image_thumb_url;
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
                ", name='" + name + '\'' +
                ", tags='" + tags + '\'' +
                ", priceInCents=" + price_in_cents +
                ", primaryCategory='" + primary_category + '\'' +
                ", origin='" + origin + '\'' +
                ", packageUnitVolumeInMilliliters=" + package_unit_volume_in_milliliters +
                ", alcoholContent=" + alcohol_content +
                ", producerName='" + producer_name + '\'' +
                ", imageThumbUrl='" + image_thumb_url + '\'' +
                ", varietal='" + varietal + '\'' +
                ", style='" + style + '\'' +
                '}';
    }

}
