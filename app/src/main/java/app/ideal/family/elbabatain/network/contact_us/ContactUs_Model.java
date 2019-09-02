package app.ideal.family.elbabatain.network.contact_us;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUs_Model {

    @SerializedName("msg")
    @Expose
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
