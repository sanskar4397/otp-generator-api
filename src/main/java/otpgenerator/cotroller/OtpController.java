package otpgenerator.cotroller;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import otpgenerator.models.RequestOtp;
import otpgenerator.models.ValidateOtpRequest;
import otpgenerator.services.IOtpService;


@RestController
public class OtpController {
    private final IOtpService _iOtpService;

    @Autowired
    public OtpController(IOtpService otpService) {
        _iOtpService = otpService;
    }

    @PostMapping("api/v1/Otp/new-otp")
    public HashMap<String, Integer> SendOtp(@RequestBody RequestOtp requestOtp) {
        // if(requestOtp.GetSendType() == null) {
        //     return 
        // }
        return _iOtpService.SendOtp(requestOtp);
    }

    @PostMapping("api/v1/Otp/validate")
    public boolean ValidateOtp(@RequestBody ValidateOtpRequest validateRequest) {
        return _iOtpService.ValidateOtp(validateRequest);
    }
    
}
