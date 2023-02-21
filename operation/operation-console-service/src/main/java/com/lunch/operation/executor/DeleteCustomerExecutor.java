package com.lunch.operation.executor;

import com.lunch.common.exception.SysException;
import com.lunch.common.executor.IExecutor;
import com.lunch.common.vo.EmptyVO;
import com.lunch.operation.dto.DeleteCustomerDTO;
import com.lunch.operation.enums.OperationErrorEnum;
import com.lunch.operation.model.Customer;
import com.lunch.operation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DeleteCustomerExecutor
 *
 * @author torrisli
 * @date 2023/2/21
 * @Description: DeleteCustomerExecutor
 */
@Component("DeleteCustomer")
public class DeleteCustomerExecutor implements IExecutor<DeleteCustomerDTO, EmptyVO> {

    @Autowired
    private CustomerService customerService;

    @Override
    public EmptyVO execute(DeleteCustomerDTO deleteCustomerDTO) throws Exception {

        // get customer by customerId
        Customer customer = customerService.getCustomerByCustomerId(deleteCustomerDTO.getCustomerId());
        if (customer == null) {
            throw new SysException(OperationErrorEnum.NOT_EXISTS_CUSTOMER);
        }

        String id = customer.getId();
        customerService.deleteCustomerById(id);

        return new EmptyVO();
    }
}
