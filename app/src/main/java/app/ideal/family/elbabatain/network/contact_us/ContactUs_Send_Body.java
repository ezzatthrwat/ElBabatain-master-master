package app.ideal.family.elbabatain.network.contact_us;

import com.google.gson.annotations.SerializedName;

public class ContactUs_Send_Body {

    @SerializedName("username")
    private String username ;
    @SerializedName("email")
    private String email ;
    @SerializedName("phone_number")
    private String phone_number ;
    @SerializedName("comment")
    private String comment ;

    public void setContactUsBody(String username , String email , String phone_number , String comment){

        this.username = username ;
        this.email = email ;
        this.phone_number = phone_number;
        this.comment = comment ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
