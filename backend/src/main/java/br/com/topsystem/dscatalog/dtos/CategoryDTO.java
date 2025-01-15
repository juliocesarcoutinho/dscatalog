package br.com.topsystem.dscatalog.dtos;

import br.com.topsystem.dscatalog.entities.Category;
import lombok.Getter;

@Getter
public class CategoryDTO {
    
    private Long id;
    private String name;
    
    public CategoryDTO() {}
    
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
