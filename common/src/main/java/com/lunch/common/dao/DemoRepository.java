package com.lunch.common.dao;

import com.lunch.common.model.DemoEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * DemoEntityRepository
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: DemoEntityRepository
 */
public interface DemoRepository extends MongoRepository<DemoEntity, String> {

    Long deleteByName(String name);

    DemoEntity findByName(String name);

    Page<DemoEntity> findAllByName(String name, Pageable pageable);

    Page<DemoEntity> findAllByOrgIn(List org, Pageable pageable);

    Page<DemoEntity> findAllByOrg(List org, Pageable pageable);

    Page<DemoEntity> findAllByOrgContaining(List org, Pageable pageable);

    boolean existsByName(String name);
}
