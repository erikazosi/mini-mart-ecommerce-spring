package edu.miu.waa.minimartecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillingAddressDto {
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private String district;
    @NotEmpty
    private String contactNo;
}
