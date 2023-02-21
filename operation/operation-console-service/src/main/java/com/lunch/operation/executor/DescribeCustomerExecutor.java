package com.lunch.operation.executor;

import com.google.common.collect.Lists;
import com.lunch.common.exception.SysException;
import com.lunch.common.executor.IExecutor;
import com.lunch.operation.dto.DescribeCustomerDTO;
import com.lunch.operation.enums.OperationErrorEnum;
import com.lunch.operation.model.Customer;
import com.lunch.operation.model.CustomerContactInfo;
import com.lunch.operation.service.CustomerService;
import com.lunch.operation.vo.CustomerContactInfoVO;
import com.lunch.operation.vo.DescribeCustomerVO;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DescribeCustomerExecutor
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: DescribeCustomerExecutor
 */
@Component("DescribeCustomer")
public class DescribeCustomerExecutor implements IExecutor<DescribeCustomerDTO, DescribeCustomerVO> {

    @Autowired
    private CustomerService customerService;

    @Override
    public DescribeCustomerVO execute(DescribeCustomerDTO describeCustomerDTO) throws Exception {

        // get customer by customerId
        String customerId = describeCustomerDTO.getCustomerId();
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        if (customer == null) {
            throw new SysException(OperationErrorEnum.NOT_EXISTS_CUSTOMER);
        }

        DescribeCustomerVO vo = new DescribeCustomerVO();
        vo.setCustomerId(customer.getCustomerId());
        vo.setCustomerName(customer.getCustomerName());
        vo.setCustomerType(customer.getType());
        vo.setRemark(customer.getRemark());
        vo.setCreatedDate(customer.getCreatedDate());
        vo.setLastModifiedDate(customer.getLastModifiedDate());

        // handle customerContactInfos
        List<CustomerContactInfo> customerContacts = customer.getCustomerContactInfos();
        if (CollectionUtils.isNotEmpty(customerContacts)) {
            List<CustomerContactInfoVO> customerContactInfoVOList = Lists.newArrayList();

            for (CustomerContactInfo contact : customerContacts) {
                CustomerContactInfoVO contactVO = new CustomerContactInfoVO();

                contactVO.setName(contact.getName());
                contactVO.setGender(contact.getGender().toUpperCase());
                contactVO.setPhone(contact.getPhone());
                contactVO.setPhoneAreaCode(contact.getPhoneAreaCode());
                contactVO.setEmail(contact.getEmail());
                contactVO.setAddress(contact.getAddress());
                customerContactInfoVOList.add(contactVO);
            }
            vo.setCustomerContactInfos(customerContactInfoVOList);
        }

        return vo;
    }
}
