package com.amazon.buspassmanagement.controller;

public class CustomException extends Exception{
    CustomException(String msg) {
        //passing the parameter to the super class constructor
        super(msg);
    }
}
