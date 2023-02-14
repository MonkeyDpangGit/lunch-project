package com.lunch.common.vo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * PageVO
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: PageVO
 */
public class PageVO<T> extends LinkedHashMap {

    /**
     * 总条数
     */
    private long totalCount;

    /**
     * 数据
     */
    public List<T> data;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 返回集合
     *
     * @param data 返回数据
     * @param totalCount 总记录数
     * @return
     */
    public static <X> PageVO<X> ok(List<X> data, long totalCount) {

        return ok(data, totalCount, null);
    }

    /**
     * 返回集合
     *
     * @param data 返回数据
     * @param totalCount 总记录数
     * @param dataAttributeName 数据的显示名称
     * @return
     */
    public static <X> PageVO<X> ok(List<X> data, long totalCount, String dataAttributeName) {

        return ok(data, totalCount, dataAttributeName, null);
    }

    /**
     * 返回集合
     *
     * @param data 返回数据
     * @param totalCount 总记录数
     * @param dataAttributeName 数据的显示名称
     * @param otherOuput 返回的其他字段信息
     * @return
     */
    public static <X> PageVO<X> ok(List<X> data, long totalCount, String dataAttributeName, Map otherOuput) {

        PageVO<X> pageVO = new PageVO<X>();

        //总数为-1，表示不开启总数查询
        if (totalCount == -1) {
            pageVO.put("totalCount", null);
        } else {
            pageVO.put("totalCount", totalCount);
        }

        if (StringUtils.isNotBlank(dataAttributeName)) {
            pageVO.put(dataAttributeName, data);
        } else {
            pageVO.put("data", data);
        }

        if (MapUtils.isNotEmpty(otherOuput)) {
            otherOuput.forEach((k, v) -> pageVO.put(k, v));
        }

        return pageVO;
    }
}
