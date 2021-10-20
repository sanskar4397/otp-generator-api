package otpgenerator.services.Helper;
import java.time.Instant;
import java.util.HashMap;
import org.springframework.util.DigestUtils;
import otpgenerator.Enums.OtpEnums.SendType;
import otpgenerator.models.SavedOtpData;
import otpgenerator.models.ValidateOtpRequest;



public class GenerateOtp {

    public static HashMap<String, Integer> ProcessNewRequest(SendType type, String sendTo) {

        String currentTimeMillis = Instant.now().toEpochMilli() + "";
        String uniqueString = getUniqueKey(currentTimeMillis, type, sendTo);
        int shortCode = getCode(uniqueString);
        return new HashMap<String, Integer>()
        {{
            put(currentTimeMillis, shortCode);
        }};
    }

    private static String getUniqueKey(String timeMillis, SendType type, String sendTo) {
        return timeMillis +  "::" + type + sendTo;
    }

    private static int getCode(String timeMillis) {
        byte[] timeInMytes = timeMillis.getBytes();
        String md5Hash = DigestUtils.md5DigestAsHex(timeInMytes);        
        return shortenHash(md5Hash, 6);
    }

    private static int shortenHash(String hash, int codeLength) {
        int hashCode = hash.hashCode();
        int hashLength = String.valueOf(hashCode).length();
        int startIndex = hashLength/3;
        int endIndex =  codeLength + startIndex;
        String stringifiedHashCode = hashCode + "";
        String generatedCodeString = stringifiedHashCode.substring(startIndex, endIndex);
        generatedCodeString = generatedCodeString.replace("1", "4");
        return Integer.parseInt(generatedCodeString);
    }

    public static boolean ValidateCode(SavedOtpData otpData, ValidateOtpRequest validateRequest) {
        
        if(otpData.IsValidOtp() &&
        otpData.GetSendType() == validateRequest.GetSendType() &&
        otpData.GetSentTo().equals(validateRequest.GetSendTo())) {
        
            // not time expired
            String uniqueKey = getUniqueKey(otpData.GetUniqueString(), otpData.GetSendType(), otpData.GetSentTo());
            int code = getCode(uniqueKey);
            return code == validateRequest.GetOtp();
        }
        else return false;
    }
    
}
