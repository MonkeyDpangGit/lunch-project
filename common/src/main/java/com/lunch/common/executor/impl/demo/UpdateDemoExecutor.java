package com.lunch.common.executor.impl.demo;

import com.lunch.common.dto.demo.UpdateDemoDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.model.DemoEntity;
import com.lunch.common.service.DemoService;
import com.lunch.common.vo.EmptyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UpdateDemoExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: UpdateDemoExecutor
 */
@Component("UpdateDemo")
public class UpdateDemoExecutor implements IExecutor<UpdateDemoDTO, EmptyVO> {

    @Autowired
    private DemoService demoService;

    @Override
    public EmptyVO execute(UpdateDemoDTO updateDemoDTO) throws Exception {

        DemoEntity demoEntity = demoService.getDemoRepository().findByName(updateDemoDTO.getName());
        if (demoEntity == null) {
            throw new IllegalArgumentException("name not exists");
        }

        String desc = updateDemoDTO.getDesc();
        if (desc != null) {
            demoEntity.setDesc(desc);
        }

//        demoEntity.setLastModifiedDate(new Date());
        demoService.getDemoRepository().save(demoEntity);

        return EmptyVO.n();
    }
}
