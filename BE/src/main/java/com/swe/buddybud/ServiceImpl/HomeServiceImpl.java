package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.HomeMapper;
import com.swe.buddybud.Service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeMapper homeMapper;


}
