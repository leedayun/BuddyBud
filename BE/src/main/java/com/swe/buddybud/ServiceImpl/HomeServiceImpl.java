package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.HomeMapper;
import com.swe.buddybud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeMapper homeMapper;


    @Override
    public void insertSNS(Map<String, String> fields) {
        homeMapper.insertSNS(fields);
    }

    @Override
    public List<Map<String, String>> getSNSsList() {
        return homeMapper.getSNSsList();
    }

    @Override
    public Map<String, String> getSNS(Integer snsId) {
        return homeMapper.getSNS(snsId);
    }

    @Override
    public void updateSNS(Integer snsId, Map<String, String> fields) {
        homeMapper.updateSNS(snsId, fields);
    }

    @Override
    public boolean deleteSNS(Integer snsId) {
        int rowsAffected = homeMapper.deleteSNS(snsId);

        return rowsAffected > 0;
    }
}
