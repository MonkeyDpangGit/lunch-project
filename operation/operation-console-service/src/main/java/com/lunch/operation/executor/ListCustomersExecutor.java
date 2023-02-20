package com.lunch.operation.executor;

import com.google.common.collect.Lists;
import com.lunch.common.dto.PageDTO;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.vo.PageVO;
import com.lunch.operation.enums.CustomerType;
import com.lunch.operation.model.Customer;
import com.lunch.operation.service.CustomerService;
import com.lunch.operation.vo.ListCustomersVO;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * ListCustomersExecutor
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: ListCustomersExecutor
 */
@Component("ListCustomers")
public class ListCustomersExecutor implements IExecutor<PageDTO, PageVO<ListCustomersVO>> {

    @Autowired
    private CustomerService customerService;

    @Override
    public PageVO<ListCustomersVO> execute(PageDTO pageDTO) throws Exception {

        // input
        Customer searchCondition = transSearchCondition(pageDTO.getSearchCondition());
        Pageable pageable = pageDTO.toPageable("createdDate");

        // execute
        Page<Customer> customerPage = customerService.getCustomerList(searchCondition, pageable);

        // outputs
        List<ListCustomersVO> resultList = Lists.newArrayList();
        for (Customer customer : customerPage.toList()) {
            ListCustomersVO vo = new ListCustomersVO();

            vo.setCustomerId(customer.getCustomerId());
            vo.setCustomerName(customer.getCustomerName());
            vo.setCreatedDate(customer.getCreatedDate());
            vo.setLastModifiedDate(customer.getLastModifiedDate());

            String type = customer.getType();
            CustomerType customerType = null;
            try {
                customerType = CustomerType.valueOf(type.toUpperCase());
            } catch (Exception e) {
                customerType = CustomerType.UNKNOWN;
            }
            vo.setCustomerType(customerType);
            vo.setCustomerTypeDisplayName(customerType.getDisplayName());

            resultList.add(vo);
        }

        return PageVO.ok(resultList, customerPage.getTotalElements(), "customerList");
    }

    /**
     * 将Map类型查询条件参数，转换为Customer类型
     *
     * @param searchCondition Map类型的查询条件
     * @return
     */
    private Customer transSearchCondition(Map searchCondition) {

        Customer customer = new Customer();
        customer.setCustomerName(MapUtils.getString(searchCondition, "customerName"));
        return customer;
    }
}
