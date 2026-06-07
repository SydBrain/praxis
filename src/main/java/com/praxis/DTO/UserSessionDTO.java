package com.praxis.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSessionDTO {
    private Long id;
    private Long experimentId;
    private LocalDateTime createdAt;
}
