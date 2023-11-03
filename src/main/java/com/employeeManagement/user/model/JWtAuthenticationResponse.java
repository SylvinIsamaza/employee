package com.employeeManagement.user.model;

import com.employeeManagement.user.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWtAuthenticationResponse {
    @JsonProperty("token")
    private  String token;
    @JsonProperty("refresh-token")
    private  String refreshToken;
    @JsonProperty("userDetails")
    private User user;
}
