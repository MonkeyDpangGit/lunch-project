package com.lunch.common.dto.demo;

import javax.validation.constraints.NotBlank;

/**
 * DeleteDemoDTO
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DeleteDemoDTO
 */
public class DeleteDemoDTO {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
