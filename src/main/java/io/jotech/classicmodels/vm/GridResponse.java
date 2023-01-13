package io.jotech.classicmodels.vm;

import java.util.List;

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
public class GridResponse<T> {
    private List<T> rows;
    private boolean success;
    private long totalCount;
}
