package br.com.topsystem.dscatalog.resources;

import br.com.topsystem.dscatalog.entities.Category;
import br.com.topsystem.dscatalog.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    
    private final CategoryService service;
    public CategoryResource(CategoryService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<Page<Category>> fidAll(Pageable pageable) {
       Page<Category> categories = service.findAll(pageable);
       return ResponseEntity.ok(categories);
    }

}
