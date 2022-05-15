package com.smartcash.engine.helpers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {
    public static ResponseEntity badRequest(){
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<List<?>> listResponse(List<?> list){
        return list.isEmpty() ? badRequest() : ResponseEntity.ok(list);
    }
}
