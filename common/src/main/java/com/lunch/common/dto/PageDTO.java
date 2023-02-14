package com.lunch.common.dto;

import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/**
 * PageDTO
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: PageDTO
 */
public class PageDTO {

    private Integer limit;

    private Integer offset;

    public Sort sort;

    public Map searchCondition;

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Map getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(Map searchCondition) {
        this.searchCondition = searchCondition;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Pageable toPageable(String defaultSortKey) {
        if (offset == null || limit == null) {
            return Pageable.unpaged();
        }
        if (sort == null) {
            return PageRequest.of(offset / limit, limit);
        } else {
            Direction sortOrder = Optional.ofNullable(sort.getSortOrder()).orElse(Direction.DESC);
            String sortKey = StringUtils.defaultString(sort.getSortKey(), defaultSortKey);
            return PageRequest.of(offset / limit, limit, sortOrder, sortKey);
        }
    }
}
