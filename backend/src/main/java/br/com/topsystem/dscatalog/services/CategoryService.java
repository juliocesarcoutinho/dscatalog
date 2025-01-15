package br.com.topsystem.dscatalog.services;

import br.com.topsystem.dscatalog.dtos.CategoryDTO;
import br.com.topsystem.dscatalog.entities.Category;
import br.com.topsystem.dscatalog.repositories.CategoryRepository;
import br.com.topsystem.dscatalog.services.exceptions.DatabaseException;
import br.com.topsystem.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    
    // find by id
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundExceptions("Entity not found"));
        return new CategoryDTO(entity);
    }
    
    // insert category
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        var entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
    
    // update category
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            var entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExceptions("Id not found " + id);
        }
    }
    
    // delete category
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            var entity = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundExceptions("Id not found " + id));
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
