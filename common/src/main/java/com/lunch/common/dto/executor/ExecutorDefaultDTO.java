package com.lunch.common.dto.executor;

import java.util.Map;
import javax.validation.constraints.NotBlank;

/**
 * ExecutorDefaultDTO
 *
 * @author torrisli
 * @date 2023/2/14
 * @Description: ExecutorDefaultDTO
 */
public class ExecutorDefaultDTO {

    @NotBlank
    private String action;

    private String requestId;

    private String language;

    private Map<String, Object> input;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }
}
