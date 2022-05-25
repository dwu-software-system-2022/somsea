package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.User;
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

    public Nft toEntity(User user, Collection collection) {
        return Nft.builder()
                  .imageUrl(imageUrl)
                  .collection(collection)
                  .user(user)
                  .build();
    }
}
