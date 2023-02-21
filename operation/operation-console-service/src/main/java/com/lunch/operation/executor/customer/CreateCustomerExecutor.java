package com.lunch.operation.executor.customer;

import com.google.common.collect.Lists;
import com.lunch.common.enums.Gender;
import com.lunch.common.enums.SysErrorEnum;
import com.lunch.common.exception.SysException;
import com.lunch.common.executor.IExecutor;
import com.lunch.operation.dto.CreateCustomerDTO;
import com.lunch.operation.dto.CustomerContactInfoDTO;
import com.lunch.operation.enums.CustomerType;
import com.lunch.operation.enums.OperationErrorEnum;
import com.lunch.operation.model.Customer;
import com.lunch.operation.model.CustomerContactInfo;
import com.lunch.operation.service.CustomerService;
import com.lunch.operation.vo.CreateCustomerVO;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CreateCustomerExecutor
 *
 * @author torrisli
 * @date 2023/2/20
 * @Description: CreateCustomerExecutor
 */
@Component("CreateCustomer")
public class CreateCustomerExecutor implements IExecutor<CreateCustomerDTO, CreateCustomerVO> {

    @Autowired
    private CustomerService customerService;

    @Override
    public CreateCustomerVO execute(CreateCustomerDTO createCustomerDTO) throws Exception {

        // generate customerId
        String customerId = customerService.generateCustomerId();

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName(createCustomerDTO.getCustomerName());
        customer.setRemark(createCustomerDTO.getRemark());

        // handle customerType
        String customerType = createCustomerDTO.getCustomerType().toUpperCase();
        try {
            CustomerType.valueOf(customerType);
        } catch (IllegalArgumentException e) {
            throw new SysException(OperationErrorEnum.ILLEGAL_CUSTOMER_TYPE);
        }
        customer.setType(customerType);

        // handle customerContactInfos
        List<CustomerContactInfoDTO> customerContactInfoDTOList = createCustomerDTO.getCustomerContactInfos();
        if (CollectionUtils.isNotEmpty(customerContactInfoDTOList)) {
            List<CustomerContactInfo> customerContactInfos = Lists.newArrayList();

            for (CustomerContactInfoDTO contactDTO : customerContactInfoDTOList) {
                String gender = contactDTO.getGender().toUpperCase();
                try {
                    Gender.valueOf(gender);
                } catch (IllegalArgumentException e) {
                    throw new SysException(SysErrorEnum.ILLEGAL_GENDER);
                }
                CustomerContactInfo contact = new CustomerContactInfo();
                contact.setGender(gender);

                contact.setName(contactDTO.getName());
                contact.setPhone(contactDTO.getPhone());
                contact.setPhoneAreaCode(contactDTO.getPhoneAreaCode());
                contact.setEmail(contactDTO.getEmail());
                contact.setAddress(contactDTO.getAddress());
                customerContactInfos.add(contact);
            }
            customer.setCustomerContactInfos(customerContactInfos);
        }

        customerService.saveCustomer(customer);

        CreateCustomerVO vo = new CreateCustomerVO();
        vo.setCustomerId(customerId);
        return vo;
    }
}
