package com.txtbravo.txtbravo.config;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.txtbravo.txtbravo.config.HttpResponse.*;

public class ValidateModel
{

    public static ResponseEntity<Object> getValidationErrors(BindingResult bindingResults)
    {
        List<String> validationError = new ArrayList<>();

        Map<String, Object> result = new HashMap<>();

        result.put(KEY_CODE.getKey(), UNPROCESSABLE_ENTITY.getCode());
        result.put(KEY_STATUS.getKey(), KEY_ERROR.getKey());

        for(ObjectError e: bindingResults.getAllErrors())
        {
            validationError.add(e.getDefaultMessage());
        }

        result.put(KEY_ERRORS.getKey(), validationError);

        //return new ResponseEntity<Object>(result, HttpStatus.UNPROCESSABLE_ENTITY);

        return ResponseEntity.unprocessableEntity().body(result);
    }


    public static ResponseEntity<?> isValidPasswordFields(Map<String, String> body, BindingResult bindingResults)
    {
        Map<String, Object> result = new HashMap<>();

        String old_password = body.get("old_password");
        String new_password = body.get("new_password");
        String confirm_password = body.get("confirm_password");

        if(old_password == null || old_password.isEmpty())
        {
            ObjectError error = new ObjectError("old_password","Old password required.");
            bindingResults.addError(error);
        }

        else if(new_password == null || new_password.isEmpty())
        {
            ObjectError error = new ObjectError("new_password","New password required.");
            bindingResults.addError(error);
        }

        else if(confirm_password == null || confirm_password.isEmpty())
        {
            ObjectError error = new ObjectError("confirm_password","Confirm password required.");
            bindingResults.addError(error);
        }

        else if (!new_password.equals(confirm_password))
        {
            ObjectError error = new ObjectError("confirmation_error","Password confirmation error.");
            bindingResults.addError(error);
        }

        if(bindingResults.hasErrors())
        {
            return ValidateModel.getValidationErrors(bindingResults);
        }

        if(!new_password.equals(confirm_password))
        {
            result.put("confirmation_error", "Password Confirmation Error");
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.accepted().build();
    }
}