package com.example.copsboot.user.web;

import com.example.copsboot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreateUserRequestValidator implements ConstraintValidator<ValidCreateUserRequest, CreateUserRequest> {

    private final UserService userService;

    @Autowired
    public CreateUserRequestValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidCreateUserRequest constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreateUserRequest userRequest, ConstraintValidatorContext context) {

        boolean result = true;

        if (userService.findUserByMobileToken(userRequest.mobileToken()).isPresent()) {
            context.buildConstraintViolationWithTemplate(
                            "There is already a user with the given mobile token.")
                    .addPropertyNode("mobileToken").addConstraintViolation();
            result = false;
        }
        return result;
    }
}

