package com.cont.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResult<e> {

    private String code;
    private String msg;
    private e data;
}
