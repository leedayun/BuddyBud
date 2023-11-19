package com.swe.buddybud.ServiceImpl;

import com.swe.buddybud.Mapper.AccountMapper;
import com.swe.buddybud.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;


}
