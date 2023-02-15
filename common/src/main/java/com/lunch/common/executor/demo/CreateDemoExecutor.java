package com.lunch.common.executor.demo;

import com.lunch.common.dto.demo.CreateDemoDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.model.DemoEntity;
import com.lunch.common.service.DemoService;
import com.lunch.common.vo.demo.CreateDemoVO;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CreateDemoExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: CreateDemoExecutor
 */
@Component("CreateDemo")
public class CreateDemoExecutor implements IExecutor<CreateDemoDTO, CreateDemoVO> {

    @Autowired
    private DemoService demoService;

    @Override
    public CreateDemoVO execute(CreateDemoDTO createDemoEntityDTO) throws Exception {

        String name = createDemoEntityDTO.getName();
        boolean exists = demoService.getDemoRepository().existsByName(name);
        if (exists) {
            throw new IllegalArgumentException("name exists");
        }

        DemoEntity demoEntity = new DemoEntity();
        Date date = new Date();
//        demoEntity.setCreatedDate(date);
//        demoEntity.setLastModifiedDate(date);
        demoEntity.setExpiredDate(date);
        demoEntity.setDesc(createDemoEntityDTO.getDesc());
        demoEntity.setName(name);
        demoEntity.setOrg(createDemoEntityDTO.getOrg());

//        String id = StringUtils.defaultString(createDemoEntityDTO.getId(), UUID.randomUUID().toString());
//        demoEntity.setId(id);

        demoService.getDemoRepository().insert(demoEntity);

        CreateDemoVO vo = new CreateDemoVO();
//        vo.setId(id);

        return vo;
    }
}
