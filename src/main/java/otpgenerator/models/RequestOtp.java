package otpgenerator.models;
import otpgenerator.Enums.OtpEnums.SendType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestOtp {



    private SendType Type ;
    private String SendTo ;


    public RequestOtp(@JsonProperty("type") String type,
                    @JsonProperty("sendTo") String sendTo) {
        this.SendTo = sendTo;
        this.Type = SendType.valueOf(type);
    }

    public SendType GetSendType() {
        return Type;
    }
    public String GetSendTo() {
        return SendTo;
    }


}





