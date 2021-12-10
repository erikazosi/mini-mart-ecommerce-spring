package edu.miu.waa.minimartecommerce.domain.user;

import com.fasterxml.jackson.annotation.JsonView;
import edu.miu.waa.minimartecommerce.view.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "billing_address")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillingAddress {
    @Id
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    @GeneratedValue(generator = "system_uuid")
    private String id;

    @JsonView({View.OrderView.class, View.UserDetailView.class})
    @NotEmpty
    @Column(name = "address")
    private String address ="";
    @JsonView({View.OrderView.class, View.UserDetailView.class})
    @NotEmpty
    private String city="";
    @JsonView({View.OrderView.class, View.UserDetailView.class})
    @NotEmpty
    private String state="";
    @JsonView({View.OrderView.class, View.UserDetailView.class})
    @NotEmpty
    @Column(name = "contact_no")
    private String contactNo="";
}
