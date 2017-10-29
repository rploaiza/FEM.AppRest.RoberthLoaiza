package es.upm.alumnos.femapprestroberthloaiza.api.manager;

/**
 * Created by Usuario on 26/10/2017.
 */

public class Key_Api {

    private static final String API_KEY="MDpjMDI2YjY0Ni1iYTQxLTExZTctODMwMi0zYjZjZWM3ZmI5N2Q6d2RlTUE3MzA4SjVXVlpzd1BvOGdpQzFGRjBKWmU3VjJmNWNW";

    private String APIKey;

    public Key_Api() {
        this.setAPIKey(API_KEY);
    }

    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

}
