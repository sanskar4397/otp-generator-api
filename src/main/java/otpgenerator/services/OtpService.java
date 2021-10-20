package otpgenerator.services;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.UUID;

import otpgenerator.dao.IOtpDao;
import otpgenerator.models.RequestOtp;
import otpgenerator.models.SavedOtpData;
import otpgenerator.models.ValidateOtpRequest;
import otpgenerator.services.Helper.GenerateOtp;

@Service
public class OtpService implements IOtpService {

    private final IOtpDao _otpDao;
    
    public OtpService(IOtpDao otpDao) {
        _otpDao = otpDao;
    }

    @Override
    public HashMap<String, Integer> SendOtp(RequestOtp requestOtp) {

        HashMap<String, Integer> createdOtpData = GenerateOtp.ProcessNewRequest(
            requestOtp.GetSendType(), requestOtp.GetSendTo());
        
        String uniqueString = createdOtpData.keySet().toArray()[0].toString();
        Integer otp = createdOtpData.get(uniqueString);        
        SavedOtpData newOtpRequest = SavedOtpData.NewOtpRequestToSaveData(
            uniqueString, requestOtp.GetSendType(), requestOtp.GetSendTo());
        String id = _otpDao.SaveNewRecord(newOtpRequest).toString();
        createdOtpData.remove(uniqueString);
        createdOtpData.put(id, otp);
        return createdOtpData;
    }

    @Override
    public boolean ValidateOtp(ValidateOtpRequest validateRequest) {
        SavedOtpData existingData = _otpDao.GetOtpData(validateRequest.GetOtpId());
        boolean isValidOtp = GenerateOtp.ValidateCode(existingData, validateRequest);
        if (isValidOtp) {
            _otpDao.DeleteRecord(existingData.GetId());
        }
        return isValidOtp;
    }
    
}
