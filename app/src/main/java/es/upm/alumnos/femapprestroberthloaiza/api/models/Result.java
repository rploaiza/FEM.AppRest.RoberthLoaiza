package es.upm.alumnos.femapprestroberthloaiza.api.models;

public class Result {

    private Integer id;
    private String name;
    private String tags;
    private Integer priceInCents;
    private String primaryCategory;
    private String origin;
    private String _package;
    private Integer packageUnitVolumeInMilliliters;
    private Integer alcoholContent;
    private String producerName;
    private String imageThumbUrl;
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
        return priceInCents;
    }

    public void setPriceInCents(Integer priceInCents) {
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

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public Integer getPackageUnitVolumeInMilliliters() {
        return packageUnitVolumeInMilliliters;
    }

    public void setPackageUnitVolumeInMilliliters(Integer packageUnitVolumeInMilliliters) {
        this.packageUnitVolumeInMilliliters = packageUnitVolumeInMilliliters;
    }

    public Integer getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(Integer alcoholContent) {
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
                ", _package='" + _package +
                ", packageUnitVolumeInMilliliters=" + packageUnitVolumeInMilliliters +
                ", alcoholContent=" + alcoholContent +
                ", producerName='" + producerName +
                ", imageThumbUrl='" + imageThumbUrl +
                ", varietal='" + varietal +
                ", style='" + style +
                '}';
    }

}
