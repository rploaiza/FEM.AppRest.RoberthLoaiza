package es.upm.alumnos.femapprestroberthloaiza.database.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Usuario on 27/10/2017.
 */

public class CommentParce implements Parcelable {

    private int id;
    private int licorsId;
    private String comment;

    public CommentParce(int id, int licorsId, String comment) {
        this.setId(id);
        this.setLicorsId(licorsId);
        this.setRanking(comment);
    }

    protected CommentParce(Parcel in) {
        this.setId(in.readInt());
        this.setLicorsId(in.readInt());
        this.setRanking(in.readString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLicorsId() {
        return licorsId;
    }

    public void setLicorsId(int licorsId) {
        this.licorsId = licorsId;
    }

    public String getRanking() {
        return comment;
    }

    public void setRanking(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentParce{" +
                "id=" + id +
                ", licorsId=" + licorsId +
                ", ranking=" + comment +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommentParce> CREATOR = new Creator<CommentParce>() {
        @Override
        public CommentParce createFromParcel(Parcel source) {
            return new CommentParce(source);
        }

        @Override
        public CommentParce[] newArray(int size) {
            return new CommentParce[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.licorsId);
        dest.writeString(this.comment);
    }
}
