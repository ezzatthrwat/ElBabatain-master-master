package app.ideal.family.elbabatain.network.contact_us;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContactUsApi {

    @POST("contact")
    Call<ContactUs_Model> sendMsgToControllPanel(@Body ContactUs_Send_Body contactUs_send_body);
}
