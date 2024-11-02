package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.net.ContentHandler;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer,Long> {
    List<Customer> findByCity(String city);
    List<Customer> findByNameAndCity(String name,String city);
}
