package com.ws1001.controllers;

import com.ws1001.models.BaseModel;
import com.ws1001.services.BaseService;
import com.ws1001.services.exceptions.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<M extends BaseModel, S extends BaseService<M, ? >> {
    protected S service;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    @ResponseBody
    public Iterable<M> all() {
        return service.all();
    }

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid M newModel) {
        try {
            M model = service.save(newModel);
            return ResponseEntity.ok(model);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }

    @ResponseBody
    public ResponseEntity getPage(@PathVariable("pageNumber") int pageNumber) {
        Pageable page = new PageRequest(pageNumber-1, 5);
        return ResponseEntity.ok(service.listAllByPage(page));
    }
}