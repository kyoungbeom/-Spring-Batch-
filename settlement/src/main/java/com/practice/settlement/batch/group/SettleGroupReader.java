package com.practice.settlement.batch.group;

import com.practice.settlement.domain.Customer;
import com.practice.settlement.domain.repository.CustomerRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Iterator;

@Component
public class SettleGroupReader implements ItemReader<Customer> {

    private final CustomerRepository customerRepository;

    private Iterator<Customer> customerIterator;
    private int pageNo = 0;

    public SettleGroupReader() {
        this.customerRepository = new CustomerRepository.Fake();
        customerIterator = Collections.emptyIterator();
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(customerIterator.hasNext()) {
            return customerIterator.next();
        }

        customerIterator = customerRepository.findAll(PageRequest.of(pageNo++, 10)).iterator();

        if(!customerIterator.hasNext()) {
            return null;
        }

        return customerIterator.next();
    }

}
