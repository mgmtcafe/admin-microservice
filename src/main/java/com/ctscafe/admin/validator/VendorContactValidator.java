package com.ctscafe.admin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VendorContactValidator implements ConstraintValidator<VendorContact, String> {

       @Override
       public void initialize(VendorContact vendorContact) { }

       @Override
       public boolean isValid(String vendor_contact, ConstraintValidatorContext cxt) 
       {
              
                return vendor_contact.matches("[0-9]{10}");
                
       }
       

}