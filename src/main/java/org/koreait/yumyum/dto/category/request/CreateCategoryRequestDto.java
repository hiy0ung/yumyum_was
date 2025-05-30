package org.koreait.yumyum.dto.category.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCategoryRequestDto {
    @NotNull
    private String categoryName;
}
