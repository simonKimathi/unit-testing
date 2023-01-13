package io.jotech.classicmodels.vm;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter

@RequiredArgsConstructor
public class ErrorResponse {
    private boolean success;
    private String msg;

}
