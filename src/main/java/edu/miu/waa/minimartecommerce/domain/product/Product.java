package edu.miu.waa.minimartecommerce.domain.product;

import com.fasterxml.jackson.annotation.JsonView;
import edu.miu.waa.minimartecommerce.view.View;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.ProductView.class, View.ProductListView.class})
    private long id;

    @JsonView({View.ProductView.class, View.ProductListView.class})
    private String name;
    @JsonView({View.ProductView.class, View.ProductListView.class})
    @Column(name = "product_code")
    private String productCode;
        @JsonView({View.ProductView.class, View.ProductListView.class})
    @Column(name = "actual_price")
    private double actualPrice;
    @JsonView({View.ProductView.class, View.ProductListView.class})
    @Column(name = "sale_price")
    private double salePrice = 0;
    @JsonView({View.ProductView.class, View.ProductListView.class})
    @Column(name = "quantity_in_stock")
    private int stockQuantity;

    @JsonView({View.ProductView.class})
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "updated_date")
    private Date updatedDate;
    @JsonView({View.ProductView.class, View.ProductEditView.class})
    @Column(columnDefinition = "text")
    private String description = "";
    @JsonView({View.ProductView.class, View.ProductEditView.class})
    @Column(columnDefinition = "text")
    private String highlights = "";

    @JsonView({View.ProductView.class})
    private int rating=1;

    @JsonView({View.ProductView.class, View.ProductListView.class})
    @OneToMany(targetEntity = ProductImages.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @Fetch(FetchMode.SELECT)
    private List<ProductImages> productImages = new ArrayList<>();

    public Product(String name, String productCode, double actualPrice,
                   double salePrice, boolean inStock, boolean displaySize,
                   Date createdDate, List<ProductImages> productImages,
                   String description, String highlights) {
        this.name = name;
        this.productCode = productCode;
        this.actualPrice = actualPrice;
        this.salePrice = salePrice;
        this.createdDate = createdDate;
        this.updatedDate = createdDate;
        this.productImages = productImages;
        this.description = description;
        this.highlights = highlights;
    }
}
