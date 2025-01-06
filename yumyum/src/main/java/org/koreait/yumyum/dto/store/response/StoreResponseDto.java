package org.koreait.yumyum.dto.store.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.yumyum.entity.Category;
import org.koreait.yumyum.entity.Store;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class StoreResponseDto {
    private String storeName;
    private String logoUrl;
    private Category category;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalTime breakStartTime;
    private LocalTime breakEndTime;
    private String address;
    private String detailAddress;
    private String detail2Address;
    private String description;


    public StoreResponseDto(Store store) {
        this.storeName = store.getStoreName();
        this.logoUrl = store.getLogoUrl();
        this.category = store.getCategory();
        this.openingTime = store.getOpeningTime();
        this.closingTime = store.getClosingTime();
        this.breakStartTime = store.getBreakStartTime();
        this.breakEndTime = store.getBreakEndTime();
        this.address = store.getAddress();
        this.detailAddress = store.getDetailAddress();
        this.detail2Address = store.getDetail2Address();
        this.description = store.getDescription();
    }
}

