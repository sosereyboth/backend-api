package com.sosereyboth.backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequestDto {

    private String bookId;
    private String userToken;

}
