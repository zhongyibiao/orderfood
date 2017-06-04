package com.xiaoweiyunchuang.orderfood.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xiaoweiyunchuang.orderfood.domain.Customer;

@Mapper
public interface CustomerMapper {
	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	Customer findCustomerById(String customerId);

	List<Customer> findCustomerByName(String name);

	List<Customer> findAllCustomer();
	
	List<Customer> findCustomerInfo(Customer customer);

//	void deleteCustomerById(String customerId);
//
//	void deleteAllCustomer();
}
