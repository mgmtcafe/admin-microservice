package com.ctscafe.admin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VendorEmailValidator implements ConstraintValidator<VendorEmail, String> {

       @Override
       public void initialize(VendorEmail vendorEmail) { }

       @Override
       public boolean isValid(String vendor_email, ConstraintValidatorContext cxt) 
       {
              
                return vendor_email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$");
                
       }
       

}