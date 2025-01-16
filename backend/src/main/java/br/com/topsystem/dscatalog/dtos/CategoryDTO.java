package br.com.topsystem.dscatalog.dtos;

import br.com.topsystem.dscatalog.entities.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryDTO {
    
    private Long id;
    
    @NotBlank(message = "O campo nome é obrigatório")
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
