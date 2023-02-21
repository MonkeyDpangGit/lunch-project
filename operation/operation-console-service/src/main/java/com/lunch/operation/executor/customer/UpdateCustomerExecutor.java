package com.lunch.operation.executor.customer;

import com.google.common.collect.Lists;
import com.lunch.common.enums.Gender;
import com.lunch.common.enums.SysErrorEnum;
import com.lunch.common.exception.SysException;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.vo.EmptyVO;
import com.lunch.operation.dto.CustomerContactInfoDTO;
import com.lunch.operation.dto.UpdateCustomerDTO;
import com.lunch.operation.enums.CustomerType;
import com.lunch.operation.enums.OperationErrorEnum;
import com.lunch.operation.model.Customer;
import com.lunch.operation.model.CustomerContactInfo;
import com.lunch.operation.service.CustomerService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UpdateCustomerExecutor
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: UpdateCustomerExecutor
 */
@Component("UpdateCustomer")
public class UpdateCustomerExecutor implements IExecutor<UpdateCustomerDTO, EmptyVO> {

    @Autowired
    private CustomerService customerService;

    @Override
    public EmptyVO execute(UpdateCustomerDTO updateCustomerDTO) throws Exception {

        // get customer by customerId
        Customer customer = customerService.getCustomerByCustomerId(updateCustomerDTO.getCustomerId());
        if (customer == null) {
            throw new SysException(OperationErrorEnum.NOT_EXISTS_CUSTOMER);
        }
        // customerName
        String customerName = updateCustomerDTO.getCustomerName();
        if (customerName != null) {
            if (StringUtils.isBlank(customerName)) {
                throw new SysException(OperationErrorEnum.BLANK_CUSTOMER_NAME);
            }
            customer.setCustomerName(customerName);
        }
        // customerType
        String customerType = updateCustomerDTO.getCustomerType();
        if (customerType != null) {
            try {
                CustomerType.valueOf(customerType);
            } catch (IllegalArgumentException e) {
                throw new SysException(OperationErrorEnum.ILLEGAL_CUSTOMER_TYPE);
            }
            customer.setType(customerType);
        }
        // remark
        String remark = updateCustomerDTO.getRemark();
        if (remark != null) {
            customer.setRemark(remark);
        }
        // customerContactInfos
        List<CustomerContactInfoDTO> customerContactInfoDTOList = updateCustomerDTO.getCustomerContactInfos();
        if (customerContactInfoDTOList != null) {
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

        // save
        customerService.saveCustomer(customer);

        return new EmptyVO();
    }
}
