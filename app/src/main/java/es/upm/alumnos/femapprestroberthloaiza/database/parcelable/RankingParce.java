package es.upm.alumnos.femapprestroberthloaiza.database.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Usuario on 27/10/2017.
 */

public class RankingParce implements Parcelable {

    private int id;
    private int licorsId;
    private int ranking;

    public RankingParce(int id, int licorsId, int ranking) {
        this.setId(id);
        this.setLicorsId(licorsId);
        this.setRanking(ranking);
    }

    protected RankingParce(Parcel in) {
        this.setId(in.readInt());
        this.setLicorsId(in.readInt());
        this.setRanking(in.readInt());
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", licorsId=" + licorsId +
                ", ranking=" + ranking +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RankingParce> CREATOR = new Creator<RankingParce>() {
        @Override
        public RankingParce createFromParcel(Parcel source) {
            return new RankingParce(source);
        }

        @Override
        public RankingParce[] newArray(int size) {
            return new RankingParce[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.licorsId);
        dest.writeInt(this.ranking);
    }
}
