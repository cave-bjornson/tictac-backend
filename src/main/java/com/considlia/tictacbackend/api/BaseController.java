package com.considlia.tictacbackend.api;

import com.considlia.tictacbackend.domain.model.AbstractEntity;
import com.considlia.tictacbackend.dto.Dto;
import com.considlia.tictacbackend.service.CrudService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class BaseController<S extends AbstractEntity, N extends Number, T extends Dto> {

    static final String PATH = "/api/v1";

    final CrudService<S, N, T> crudService;
//    final Class<T> targetClass;

    protected BaseController(CrudService<S, N, T> crudService) {
        this.crudService = crudService;
//        this.targetClass = targetClass;
    }

    ResponseEntity<List<T>> getAll() {
        List<T> responseDtos = crudService.getAll();
        return responseDtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(responseDtos);
    }

    ResponseEntity<T> get(N id) {
        return ResponseEntity.ok(crudService.getDto(id));
    }

    ResponseEntity<T> update(N id, T dto) {
        return ResponseEntity.ok(crudService.update(id, dto));
    }
}
