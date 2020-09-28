package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Customer;

public interface CustomerDao {
    public Customer save(Customer customer);
    public Customer findById(int id);
    public Customer update(Customer customer);
    public void delete(Customer customer);
}
