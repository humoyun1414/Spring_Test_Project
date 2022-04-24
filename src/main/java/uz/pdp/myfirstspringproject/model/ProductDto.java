package uz.pdp.myfirstspringproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotNull(message = "The name should not be empty")
    private String name;

    @NotNull(message = "Expiration Date not empty ")
    private Integer expirationDate;

    @NotNull(message = "type not be empty")
    private String type;


}
