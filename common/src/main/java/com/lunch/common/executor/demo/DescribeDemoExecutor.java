package com.lunch.common.executor.demo;

import com.lunch.common.dto.demo.DescribeDemoDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.model.DemoEntity;
import com.lunch.common.service.DemoService;
import com.lunch.common.vo.demo.DescribeDemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DescribeDemoExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DescribeDemoExecutor
 */
@Component("DescribeDemo")
public class DescribeDemoExecutor implements IExecutor<DescribeDemoDTO, DescribeDemoVO> {

    @Autowired
    private DemoService demoService;

    @Override
    public DescribeDemoVO execute(DescribeDemoDTO describeDemoDTO) throws Exception {

        String name = describeDemoDTO.getName();
        boolean exists = demoService.getDemoRepository().existsByName(name);
        if (!exists) {
            throw new IllegalArgumentException("name not exists");
        }

        DemoEntity demoEntity = demoService.getDemoRepository().findByName(name);

        DescribeDemoVO vo = new DescribeDemoVO();
        vo.setDesc(demoEntity.getDesc());
        vo.setName(demoEntity.getName());
        vo.setId(demoEntity.getId());

        return vo;
    }
}
