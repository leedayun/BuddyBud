package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Service.WillowService;
import com.swe.buddybud.Mapper.WillowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WillowServiceImpl implements WillowService {

    @Autowired
    WillowMapper willowMapper;


    @Override
    public void sendWillow(Map<String, String> fields) {
        willowMapper.sendWillow(fields);
    }

    @Override
    public List<Map<String, String>> getSentWillowList(Integer userId) {
        return willowMapper.getSentWillowList(userId);
    }

    @Override
    public List<Map<String, String>> getReceivedWillowList(Integer userId) {
        return willowMapper.getReceivedWillowList(userId);
    }

    @Override
    public void acceptWillow(Integer userId) {
        willowMapper.acceptWillow(userId);
    }
}
