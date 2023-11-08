package com.cognizant.bms.customer.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {

    Customer customer = new Customer();

    @Test
    public void setUserNameTest(){
        customer.setUserName("abc");
        assertEquals("abc",customer.getUserName());
    }

    @Test
    public void setPasswordTest(){
        customer.setPassword("abc");
        assertEquals("abc",customer.getPassword());
    }

    @Test
    public void setNameTest(){
        customer.setName("abc");
        assertEquals("abc",customer.getName());
    }

    @Test
    public void setEmailTest(){
        customer.setEmailId("abc@mail.com");
        assertEquals("abc@mail.com",customer.getEmailId());
    }

    @Test
    public void setPanTest(){
        customer.setPan("abc");
        assertEquals("abc",customer.getPan());
    }

    @Test
    public void setContactTest(){
        customer.setContact(999999999);
        assertEquals(999999999,customer.getContact());
    }
    @Test
    public void setDobTest(){
        customer.setDob(new Date());
        assertEquals(new Date(),customer.getDob());
    }

    @Test
    public void setAddressTest(){
        customer.setAddress("address");
        assertEquals("address",customer.getAddress());
    }

    @Test
    public void setStateTest(){
        customer.setState("address");
        assertEquals("address",customer.getState());
    }

    @Test
    public void setCountryTest(){
        customer.setCountry("address");
        assertEquals("address",customer.getCountry());
    }

    @Test
    public void setBranchTest(){
        customer.setBranchName("branch");
        assertEquals("branch",customer.getBranchName());
    }

}
