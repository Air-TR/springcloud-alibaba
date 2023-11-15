package com.tr.customer.jpa;

import com.tr.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: TR
 */
public interface CustomerJpa extends JpaRepository<Customer, Integer> {

    @Query("select c from Customer c where c.id = ?1")
    Customer selectById(Integer id);

}
