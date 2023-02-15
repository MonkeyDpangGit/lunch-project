package com.lunch.common.executor.demo;

import com.lunch.common.dto.demo.DeleteDemoDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.service.DemoService;
import com.lunch.common.vo.EmptyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DeleteDemoExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DeleteDemoExecutor
 */
@Component("DeleteDemo")
public class DeleteDemoExecutor implements IExecutor<DeleteDemoDTO, EmptyVO> {

    @Autowired
    private DemoService demoService;

    @Override
    public EmptyVO execute(DeleteDemoDTO deleteDemoDTO) throws Exception {

        String name = deleteDemoDTO.getName();
        boolean exists = demoService.getDemoRepository().existsByName(name);
        if (!exists) {
            throw new IllegalArgumentException("name not exists");
        }

        demoService.getDemoRepository().deleteByName(name);
        return EmptyVO.n();
    }
}
