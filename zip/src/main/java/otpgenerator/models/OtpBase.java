package otpgenerator.models;
import java.util.UUID;


public class OtpBase {

    private UUID _id;
    private String _uniqueString;

    public OtpBase(UUID id, String uniqueString) {
        this._id = id;
        this._uniqueString = uniqueString;
    }



    //#region get
    public UUID GetId() {
        return this._id;
    }
    public String GetUniqueString() {
        return this._uniqueString;
    }
    public OtpBase GetOtpBaseData() {
        return new OtpBase(this._id, this._uniqueString);
    }


    //#endregion




    
}
