package br.com.topsystem.dscatalog.resources;


import br.com.topsystem.dscatalog.dtos.ProductDTO;
import br.com.topsystem.dscatalog.projections.ProductProjection;
import br.com.topsystem.dscatalog.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    private final ProductService service;

    public ProductResource(ProductService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
//        Page<ProductDTO> list = service.findAllPaged(pageable);
//        return ResponseEntity.ok().body(list);
//    }

    // metodo personalizado para buscar produtos
//    @GetMapping
//    public ResponseEntity<Page<ProductProjection>> findAll(@RequestParam(value = "name", defaultValue = "") String name,
//                                                          @RequestParam(value = "categoryId", defaultValue = "0") String categoryId,
//                                                          Pageable pageable) {
//        Page<ProductProjection> list = service.findAllPaged(name, categoryId, pageable);
//        return ResponseEntity.ok().body(list);
//    }
    // metodo personalizado para buscar produtos com categorias
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(value = "name", defaultValue = "") String name,
                                                          @RequestParam(value = "categoryId", defaultValue = "0") String categoryId,
                                                          Pageable pageable) {
        Page<ProductDTO> list = service.findAllPagedWithCategories(name, categoryId, pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id) {
        var dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    public ResponseEntity<ProductDTO> insert(@RequestBody @Valid ProductDTO dto) {
        dto = service.insert(dto);        
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
    
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
