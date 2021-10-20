package otpgenerator.dao;
import java.util.Hashtable;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import otpgenerator.models.SavedOtpData;

@Repository
public class OtpDao implements IOtpDao {

    private Hashtable<UUID, SavedOtpData> _savedDataTable ;
    
    public OtpDao() {
        _savedDataTable = new Hashtable<UUID,SavedOtpData>();
    }


    public UUID SaveNewRecord(SavedOtpData newData) {
        
        deleteBottomSet();
        newData = SavedOtpData.PopulateNewId(newData);
        _savedDataTable.put(newData.GetId(), newData);
        return newData.GetId();
    }

    public SavedOtpData GetOtpData(UUID id) {

        boolean ifExists = _savedDataTable.containsKey(id);
        if(ifExists) {
            return _savedDataTable.get(id);
        }
        else {
            return SavedOtpData.GetEmptyData() ;
        }
    }


    public void SetOtpAsInvalid(UUID id) {
        SavedOtpData data = _savedDataTable.get(id);
        data = SavedOtpData.SetOtpAsInvalid(data);
        _savedDataTable.remove(id);
        _savedDataTable.put(id, data);
    }
    public void DeleteRecord(UUID id) {
        _savedDataTable.remove(id);
    }


    private void deleteBottomSet() {
        int tableSize = _savedDataTable.size();

        if(tableSize > 100) {
            Object key = _savedDataTable.keySet().toArray()[0];
            _savedDataTable.remove(key);
        }
    }
    
}
