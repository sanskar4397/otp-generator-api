package otpgenerator.models;
import java.util.UUID;

import otpgenerator.Enums.OtpEnums.SendType;

public class SavedOtpData extends OtpBase{

    private SendType _sendType;
    private String _sentTo;
    private boolean _isValid;


    public boolean IsValidOtp() {
        return this._isValid;
    } 
    public String GetSentTo() {
        return this._sentTo;
    }
    public SendType GetSendType() {
        return this._sendType;
    }



    private SavedOtpData(UUID id, String uniqueString, SendType type, String sentTo, boolean isValid) {
        super(id, uniqueString);
        this._sendType = type;
        this._sentTo = sentTo;
        this._isValid = isValid;
    }



    public static SavedOtpData NewOtpRequestToSaveData(String uniqueString, SendType type, String sentTo) {
        return new SavedOtpData(null, uniqueString, type, sentTo, true);
    }
    public static SavedOtpData PopulateNewId(SavedOtpData otpDataRequest) {
        UUID newID = UUID.randomUUID();
        return new SavedOtpData(newID, otpDataRequest.GetUniqueString(), otpDataRequest._sendType, otpDataRequest._sentTo, otpDataRequest._isValid);
    }
    public static SavedOtpData GetEmptyData() {
        return new SavedOtpData(null, "", null, "", false);
    }
    public static SavedOtpData SetOtpAsInvalid(SavedOtpData otpDataRequest) {
        return new SavedOtpData(otpDataRequest.GetId(), otpDataRequest.GetUniqueString(), otpDataRequest._sendType, otpDataRequest._sentTo, false);
    }
    
}
