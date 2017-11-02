package es.upm.alumnos.femapprestroberthloaiza.database.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import static es.upm.alumnos.femapprestroberthloaiza.api.manager.Key_Api.API_KEY;

/**
 * Created by Usuario on 1/11/2017.
 */

public class ApiKeyParce implements Parcelable{

    private int id;
    private String apikey;

    public ApiKeyParce() {
        this.setApikey(API_KEY);
    }

    public ApiKeyParce(int id, String apikey){
        this.setid(id);
        this.setApikey(apikey);
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.apikey);
    }


    protected ApiKeyParce(Parcel in) {
        this.id = in.readInt();
        this.apikey = in.readString();
    }

    @Override
    public String toString() {
        return "ApiKeyParce{" +
                "id=" + id +
                ", apikey='" + apikey + '\'' +
                '}';
    }

    public static final Creator<ApiKeyParce> CREATOR = new Creator<ApiKeyParce>() {
        @Override
        public ApiKeyParce createFromParcel(Parcel source) {
            return new ApiKeyParce(source);
        }

        @Override
        public ApiKeyParce[] newArray(int size) {
            return new ApiKeyParce[size];
        }
    };
}
