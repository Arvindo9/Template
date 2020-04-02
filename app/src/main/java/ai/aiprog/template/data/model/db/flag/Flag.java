
package ai.aiprog.template.data.model.db.flag;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "flag")
public class Flag implements Parcelable {
    @Expose
    @PrimaryKey
    public Long _id;

    @SerializedName("Id")
    @Expose
    @ColumnInfo(name = "Id")
    private Integer id;
    @SerializedName("Country")
    @Expose
    @ColumnInfo(name = "Country")
    private String country;
    @SerializedName("Capital")
    @Expose
    @ColumnInfo(name = "Capital")
    private String capital;
    @SerializedName("Continent")
    @Expose
    @ColumnInfo(name = "Continent")
    private String continent;
    @SerializedName("ISOCode1")
    @Expose
    @ColumnInfo(name = "ISOCode1")
    private String iSOCode1;
    @SerializedName("ISOCode2")
    @Expose
    @ColumnInfo(name = "ISOCode2")
    private String iSOCode2;
    @SerializedName("MobilePhonePrefix")
    @Expose
    @ColumnInfo(name = "MobilePhonePrefix")
    private String mobilePhonePrefix;
    @SerializedName("FlagUrl")
    @Expose
    @ColumnInfo(name = "FlagUrl")
    private String flagUrl;

    @SerializedName("FlagBaseUrl")
    @Expose
    @ColumnInfo(name = "FlagBaseUrl")
    private String flagBaseUrl = "null";

    public Flag(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getISOCode1() {
        return iSOCode1;
    }

    public void setISOCode1(String iSOCode1) {
        this.iSOCode1 = iSOCode1;
    }

    public String getISOCode2() {
        return iSOCode2;
    }

    public void setISOCode2(String iSOCode2) {
        this.iSOCode2 = iSOCode2;
    }

    public String getMobilePhonePrefix() {
        return mobilePhonePrefix;
    }

    public void setMobilePhonePrefix(String mobilePhonePrefix) {
        this.mobilePhonePrefix = mobilePhonePrefix;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    public String getFlagBaseUrl() {
        return flagBaseUrl;
    }

    public void setFlagBaseUrl(String flagBaseUrl) {
        this.flagBaseUrl = flagBaseUrl;
    }

    //Parcel-----------------------

    protected Flag(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Flag> CREATOR = new Creator<Flag>() {
        @Override
        public Flag createFromParcel(Parcel in) {
            return new Flag(in);
        }

        @Override
        public Flag[] newArray(int size) {
            return new Flag[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeInt(id);
        dest.writeString(country);
        dest.writeString(capital);
        dest.writeString(continent);
        dest.writeString(iSOCode1);
        dest.writeString(iSOCode2);
        dest.writeString(mobilePhonePrefix);
        dest.writeString(flagUrl);
        dest.writeString(flagBaseUrl);
    }

    private void readFromParcel(Parcel in) {
        _id = in.readLong();
        id = in.readInt();
        country = in.readString();
        capital = in.readString();
        continent = in.readString();
        iSOCode1 = in.readString();
        iSOCode2 = in.readString();
        mobilePhonePrefix = in.readString();
        flagUrl = in.readString();
        flagBaseUrl = in.readString();
    }
}
