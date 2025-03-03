package br.com.topsystem.dscatalog.services;

import br.com.topsystem.dscatalog.dtos.CategoryDTO;
import br.com.topsystem.dscatalog.dtos.ProductDTO;
import br.com.topsystem.dscatalog.entities.Product;
import br.com.topsystem.dscatalog.projections.ProductProjection;
import br.com.topsystem.dscatalog.repositories.CategoryRepository;
import br.com.topsystem.dscatalog.repositories.ProductRepository;
import br.com.topsystem.dscatalog.services.exceptions.DatabaseException;
import br.com.topsystem.dscatalog.services.exceptions.ResourceNotFoundExceptions;
import br.com.topsystem.dscatalog.util.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(ProductDTO::new);
    }

//    @Transactional(readOnly = true)
//    public Page<ProductProjection> findAllPaged(String name, String categoryId, Pageable pageable) {
//        List<Long> categoryIds = List.of();
//        if (!"0".equals(categoryId)) {
//            categoryIds = Arrays.stream(categoryId.split(",")).map(Long::parseLong).toList();
//        }
//        return repository.searchProducts(categoryIds, name, pageable);
//    }

    // Products with categories
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPagedWithCategories(String name, String categoryId, Pageable pageable) {
        List<Long> categoryIds = List.of();
        if (!"0".equals(categoryId)) {
            categoryIds = Arrays.stream(categoryId.split(",")).map(Long::parseLong).toList();
        }
        Page<ProductProjection> page = repository.searchProducts(categoryIds, name.trim(), pageable);
        List<Long> productIds = page.map(ProductProjection::getId).toList();
        List<Product> entities = repository.searchProductsWithCategories(productIds);


        entities = (List<Product>) Utils.replace(page.getContent(), entities);

        List<ProductDTO> dtos = entities.stream().map(x -> new ProductDTO(x, x.getCategories())).toList();

        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundExceptions("Entity not found"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        var entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            var entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExceptions("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            var entity = repository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundExceptions("Id not found " + id));
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
    
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            var category = categoryRepository.getReferenceById(catDTO.getId());
            entity.getCategories().add(category);
        }
    }
}
