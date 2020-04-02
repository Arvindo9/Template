
package ai.aiprog.template.data.model.apis.flag;

import ai.aiprog.template.data.model.db.flag.Flag;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("FlagBaseUrl")
    @Expose
    private String flagBaseUrl;

    @SerializedName("DataList")
    @Expose
    private List<Flag> flag;

    public String getFlagBaseUrl() {
        return flagBaseUrl;
    }

    public void setFlagBaseUrl(String flagBaseUrl) {
        this.flagBaseUrl = flagBaseUrl;
    }

    public List<Flag> getFlag() {
        return flag;
    }

    public void setFlag(List<Flag> flag) {
        this.flag = flag;
    }

}
