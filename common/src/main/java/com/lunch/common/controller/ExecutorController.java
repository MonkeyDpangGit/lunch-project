package com.lunch.common.controller;

import com.lunch.common.constants.ExecutorConstants;
import com.lunch.common.dto.executor.ExecutorDefaultDTO;
import com.lunch.common.service.ExecutorService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Map<String, Object> execute(@RequestBody ExecutorDefaultDTO executorDefaultDTO) {

        String action = executorDefaultDTO.getAction();
        String requestId = executorDefaultDTO.getRequestId();
        Map<String, Object> input = executorDefaultDTO.getInput();

        return executorService.execute(action, requestId, input);
    }

    @PostMapping("/execute/{action}")
    public Map<String, Object> execute(@PathVariable(value = "action") String action,
            @RequestBody(required = false) Map<String, Object> requestBody, HttpServletRequest request) {

        String requestId = request.getHeader(ExecutorConstants.REQUEST_ID);

        return executorService.execute(action, requestId, requestBody);
    }
}
