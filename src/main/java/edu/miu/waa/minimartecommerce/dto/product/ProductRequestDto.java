package edu.miu.waa.minimartecommerce.dto.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductRequestDto {
    private String id="";
    private String productCode="";
    private String name="";
    private double actualPrice =0;
    private double salePrice=0;
    private int stockQuantity;
    private String description = "";
    private String highlights = "";
}
