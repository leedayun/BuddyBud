package com.swe.buddybud.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public interface HomeService {
    void insertSNS(Map<String, String> fields);

    List<Map<String, String>> getSNSsList();

    Map<String, String> getSNS(Integer snsId);

    void updateSNS(Integer snsId, Map<String, String> fields);

    boolean deleteSNS(Integer snsId);
}
