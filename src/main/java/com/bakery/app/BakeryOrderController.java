package com.bakery.app;


import com.bakery.model.BakeryOrder;
import com.bakery.service.OrderGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/generate-order")
public class BakeryOrderController {

    @Autowired
    private OrderGenerator orderGenerator;

    @PostMapping
    @ResponseBody
    public String generateOrderList(@RequestBody List<BakeryOrderRequest> bakeryOrderRequestList) {

            List<BakeryOrder> bakeryOrderList = orderGenerator.generateOrderList(bakeryOrderRequestList);
            return jsonConverter(bakeryOrderList);
    }

    @ControllerAdvice
    public class RestResponseEntityExceptionHandler
            extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value
                = { IllegalArgumentException.class, IllegalStateException.class })
        protected ResponseEntity<Object> handleConflict(
                RuntimeException ex, WebRequest request) {
            String bodyOfResponse = "There was an error with the request - " + ex.getMessage();
            return handleExceptionInternal(ex, bodyOfResponse,
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        }
    }

    private String jsonConverter(List<BakeryOrder> bakeryOrderRequest) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bakeryOrderRequest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
