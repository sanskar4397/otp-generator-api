package otpgenerator.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidateOtpRequest extends RequestOtp {

    private int _otp;
    private UUID _otpId;

    public ValidateOtpRequest(
        @JsonProperty("type") String type,
        @JsonProperty("sendTo") String sendTo, 
        @JsonProperty("otp") int otp,
        @JsonProperty("otpId") String otpId) {
        
        super(type, sendTo);
        this._otp = otp;
        this._otpId = UUID.fromString(otpId);
    }


    public int GetOtp() {
        return this._otp;
    }
    public UUID GetOtpId() {
        return this._otpId; 
    }
}