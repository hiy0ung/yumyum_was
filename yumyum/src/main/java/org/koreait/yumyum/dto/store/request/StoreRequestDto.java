package org.koreait.yumyum.dto.store.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.Category;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class StoreRequestDto {

    @NotNull
    private String storeName;

    @NotNull
    private String logoUrl;

    @NotNull
    private Category category;

    @NotNull
    private LocalTime openingTime;

    @NotNull
    private LocalTime closingTime;

    private LocalTime breakStartTime;

    private LocalTime breakEndTime;

    private String address;

    private String description;
}

