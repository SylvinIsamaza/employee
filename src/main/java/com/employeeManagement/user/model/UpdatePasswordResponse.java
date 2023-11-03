package com.employeeManagement.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePasswordResponse {
    @JsonProperty("msg")
    private  String msg;
    @JsonProperty("user-email")
    private  String email;

}
