package br.com.topsystem.dscatalog.services;

import br.com.topsystem.dscatalog.dtos.CategoryDTO;
import br.com.topsystem.dscatalog.entities.Category;
import br.com.topsystem.dscatalog.repositories.CategoryRepository;
import br.com.topsystem.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoryService {
    
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    
    // findAll Categories
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> list = repository.findAll(pageable);
        return list.map(CategoryDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundExceptions("Entity not found"));
        return new CategoryDTO(entity);
    }
}
