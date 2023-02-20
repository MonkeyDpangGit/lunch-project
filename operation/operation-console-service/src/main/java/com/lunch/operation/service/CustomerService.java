package com.lunch.operation.service;

import com.lunch.common.model.DemoEntity;
import com.lunch.operation.model.Customer;
import com.lunch.operation.repository.CustomerRepository;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * CustomerService
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: CustomerService
 */
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * 获取客户列表
     *
     * @param searchCondition 客户查询条件，支持客户名模糊匹配
     * @param pageable 分页条件
     * @return
     */
    public Page<Customer> getCustomerList(Customer searchCondition, Pageable pageable) {

        Page<Customer> customerList = null;

        String customerName = searchCondition.getCustomerName();
        if (StringUtils.isNotBlank(customerName)) {
            customerList = customerRepository.findAllByCustomerNameLike(customerName, pageable);
        } else {
            customerList = customerRepository.findAll(pageable);
        }
        return customerList;
    }

}
