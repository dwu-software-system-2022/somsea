package com.project.somsea.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class NftDto {
    private Long collectionId;
    private String imageUrl;
    private List<Long> partIds;
}
