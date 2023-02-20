package com.lunch.operation.repository;

import com.lunch.operation.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CustomerRepository
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: CustomerRepository
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByCustomerId(String customerId);

    Page<Customer> findAllByCustomerNameLike(String customerName, Pageable pageable);
}
