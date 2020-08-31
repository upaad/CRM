package com.hjf.annotation.support;

import com.hjf.annotation.DataValidator;
import com.hjf.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataVal implements ConstraintValidator<DataValidator, Customer> {
    private static final Logger logger = LoggerFactory.getLogger(DataValidator.class);

    @Override
    public void initialize(DataValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        if (customer.getCusname().equals("")) {
            logger.error("数据不能为空");
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,1,3,6-8])|(18[0-9])|(19[8,9])|(166))[0-9]{8}$");
        Matcher m = p.matcher(customer.getTel());
        return m.matches();
    }
}
