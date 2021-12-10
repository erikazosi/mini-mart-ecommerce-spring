package edu.miu.waa.minimartecommerce.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.miu.waa.minimartecommerce.view.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "shipping_address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShippingAddress implements Serializable {
    private static final long serialVersionUID = 2828114560584386892L;
    @Id
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @GeneratedValue(generator = "system_uuid")
    @JsonView(View.OrderView.class)
    private String id;

    @NotEmpty
    @Column(name="customer_name")
    @JsonView(View.OrderView.class)
    private String customerName ="";
    @NotEmpty
    @Column(columnDefinition = "text")
    @JsonView(View.OrderView.class)
    private String address ="";
    @NotEmpty
    @JsonView(View.OrderView.class)
    private String city="";
    @NotEmpty
    @JsonView(View.OrderView.class)
    private String district="";
    @NotEmpty
    @Column(name = "contact_no")
    @JsonView(View.OrderView.class)
    private String contactNo="";

    @JsonIgnore
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
