package com.lunch.common.executor.demo;

import com.lunch.common.dto.PageDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.model.DemoEntity;
import com.lunch.common.service.DemoService;
import com.lunch.common.vo.PageVO;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * ListDemosExecutor
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ListDemosExecutor
 */
@Component("ListDemos")
public class ListDemosExecutor implements IExecutor<PageDTO, PageVO<DemoEntity>> {

    @Autowired
    private DemoService demoService;

    @Override
    public PageVO<DemoEntity> execute(PageDTO pageDTO) throws Exception {

        Map searchCondition = pageDTO.getSearchCondition();
        Pageable pageable = pageDTO.toPageable("id");

        Page<DemoEntity> demoEntitieList = null;

        String name = MapUtils.getString(searchCondition, "name");
        List org = (List) MapUtils.getObject(searchCondition, "org");
        if (StringUtils.isNotBlank(name)) {
            demoEntitieList = demoService.getDemoRepository().findAllByName(name, pageable);
        } else if (CollectionUtils.isNotEmpty(org)) {
            demoEntitieList = demoService.getDemoRepository().findAllByOrgIn(org, pageable);
        } else {
            demoEntitieList = demoService.getDemoRepository().findAll(pageable);
        }

        return PageVO.ok(demoEntitieList.toList(), demoEntitieList.getTotalElements(), "entitieList");
    }
}
