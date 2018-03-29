package com.ctscafe.admin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VendorNameValidator implements ConstraintValidator<VendorName, String> {

       @Override
       public void initialize(VendorName vendorName) { }

       @Override
       public boolean isValid(String vendor_name, ConstraintValidatorContext cxt) {
              
              
              return vendor_name.matches("[a-zA-Z0-9 ]{1,50}");
       }
       

}

