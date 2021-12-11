package edu.miu.waa.minimartecommerce.dto.user;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse {
    private long userId;
    private String token = "";
    private String username="";
    private List<String> roles;
}
