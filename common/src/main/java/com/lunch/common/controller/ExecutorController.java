package com.lunch.common.controller;

import com.lunch.common.constants.ExecutorConstants;
import com.lunch.common.service.ExecutorService;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExecutorController
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ExecutorController
 */
@RestController()
public class ExecutorController {

    @Autowired
    private ExecutorService executorService;

    @PostMapping("/execute")
    public Map<String, Object> execute(@RequestBody Map<String, Object> requestBody) {

        String action = MapUtils.getString(requestBody, ExecutorConstants.ACTION);
        String requestId = MapUtils.getString(requestBody, ExecutorConstants.REQUEST_ID);
        Map input = MapUtils.getMap(requestBody, ExecutorConstants.INPUT);

        return executorService.execute(action, requestId, input);
    }
}
