package com.mentorship.tickets.controller;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryRestController extends BaseController<CategoryDto>{
    @Autowired
    public CategoryRestController(@Qualifier("categoryService") BaseService<CategoryDto> baseService) {
        super(baseService);
    }
}
