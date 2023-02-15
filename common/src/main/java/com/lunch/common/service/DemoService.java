package com.lunch.common.service;

import com.lunch.common.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DemoService
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DemoService
 */
@Component
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public DemoRepository getDemoRepository() {
        return demoRepository;
    }
}
