package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Service.WillowService;
import com.swe.buddybud.Mapper.WillowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WillowServiceImpl implements WillowService {

    @Autowired
    WillowMapper willowMapper;


}
