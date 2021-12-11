package edu.miu.waa.minimartecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {
    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String contactNo;
}
