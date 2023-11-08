package com.cognizant.bms.customer.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(){
        super();
    }
    public CustomerAlreadyExistsException(String message){
        super(message);
    }
}
