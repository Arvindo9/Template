package ai.aiprog.template.base.views;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author       : Arvindo Mondal
 * Created on   : 20-10-2019
 * Email        : arvindo@aiprog.ai
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.ai
 */
public class BaseModelView implements Parcelable {
    private int id;
    private String ids;
    private String key;
    private String data;

    public BaseModelView(int id, String key, String data) {
        this.id = id;
        this.key = key;
        this.data = data;
    }

    public BaseModelView(String ids, String key, String data) {
        this.ids = ids;
        this.key = key;
        this.data = data;
    }


    public BaseModelView(String ids) {
        this.ids = ids;
    }

    public BaseModelView(int id) {
        this.id = id;
    }

    public BaseModelView(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public BaseModelView(String ids, String data) {
        this.ids = ids;
        this.data = data;
    }

    protected BaseModelView(Parcel in) {
        id = in.readInt();
        ids = in.readString();
        key = in.readString();
        data = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static final Creator<BaseModelView> CREATOR = new Creator<BaseModelView>() {
        @Override
        public BaseModelView createFromParcel(Parcel in) {
            return new BaseModelView(in);
        }

        @Override
        public BaseModelView[] newArray(int size) {
            return new BaseModelView[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ids);
        dest.writeString(key);
        dest.writeString(data);
    }
}
