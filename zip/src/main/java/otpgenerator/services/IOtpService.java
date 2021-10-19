package otpgenerator.services;

import java.util.HashMap;

import otpgenerator.models.RequestOtp;
import otpgenerator.models.ValidateOtpRequest;

public interface IOtpService {
    
    HashMap<String, Integer> SendOtp(RequestOtp requestOtp); 
    boolean ValidateOtp(ValidateOtpRequest validateRequest);
}
