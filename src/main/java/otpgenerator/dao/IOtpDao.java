package otpgenerator.dao;
import java.util.UUID;

import otpgenerator.models.SavedOtpData;

public interface IOtpDao {
    UUID SaveNewRecord(SavedOtpData newData);
    SavedOtpData GetOtpData(UUID id);
    void SetOtpAsInvalid(UUID id);
    void DeleteRecord(UUID id);
    
}
