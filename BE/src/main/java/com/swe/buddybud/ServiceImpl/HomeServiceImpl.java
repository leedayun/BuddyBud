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
    public void insertHome(Map<String, String> fields) {
        homeMapper.insertHome(fields);
    }

    @Override
    public List<Map<String, String>> getHomesList() {
        return homeMapper.getHomesList();
    }

    @Override
    public Map<String, String> getHome(Integer homeId) {
        return homeMapper.getHome(homeId);
    }

    @Override
    public void updateHome(Integer homeId, Map<String, String> fields) {
        homeMapper.updateHome(homeId, fields);
    }

    @Override
    public boolean deleteHome(Integer homeId) {
        int rowsAffected = homeMapper.deleteHome(homeId);

        return rowsAffected > 0;
    }
}
