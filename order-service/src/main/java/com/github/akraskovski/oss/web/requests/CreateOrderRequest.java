package com.github.akraskovski.oss.web.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrderRequest {

    @Min(0)
    private BigDecimal price;
    @NotBlank
    private String type;
}
