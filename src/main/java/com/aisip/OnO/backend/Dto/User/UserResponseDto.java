package com.aisip.OnO.backend.Dto.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 값이 아닌 필드만 포함
public class UserResponseDto {

    private Long userId;

    private String userName;

    private String userEmail;
}
