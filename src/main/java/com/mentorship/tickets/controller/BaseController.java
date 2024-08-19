package com.mentorship.tickets.controller;

import com.mentorship.tickets.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<D> {

    protected final BaseService<D> baseService;
    protected BaseController(BaseService<D> baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(baseService.findAll());
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<D>> findPage(@RequestParam(defaultValue = "1") final int page,
                                            @RequestParam(defaultValue = "10") final int size) {
        return ResponseEntity.ok(baseService.findpage(page, size));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<D> findOneById(@PathVariable final Integer id) {
        return ResponseEntity.ok(baseService.findOneById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<D> save(@RequestBody @Valid D dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(baseService.save(dto));
    }

    @PostMapping("/multi")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<D>> saveMultipleItems(@RequestBody List<@Valid D> dTOs) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(baseService.saveMultipleItems(dTOs));
    }

    @PostMapping("/multi-async")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveMultipleItemsAsynchronously(@RequestBody List<@Valid D> dTOs) {
        baseService.saveMultipleItemsAsynchronously(dTOs);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<D> deleteById(@PathVariable final Integer id) {
        baseService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
