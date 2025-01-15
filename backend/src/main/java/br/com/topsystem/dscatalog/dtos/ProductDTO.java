package br.com.topsystem.dscatalog.dtos;

import br.com.topsystem.dscatalog.entities.Category;
import br.com.topsystem.dscatalog.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    @Size(min = 5, max = 50, message = "O nome deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;
    private String description;
    @Positive(message = "O preço deve ser um valor Positivo")
    private Double price;
    private String imgUrl;
    @PastOrPresent(message = "A data não pode ser anterior a atual")
    private Instant date;
    private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        date = entity.getDate();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryDTO(cat)));
    }

}
