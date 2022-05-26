package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;

import java.util.List;

public class NftDto {

    @Setter
    @Getter
    @Builder
    public static class Request {
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

    @Getter
    @Builder
    public static class ResponseByCollection {
        // UserId, NftId, ImageUrl, NftTitle
        private Long userId;
        private Long nfTId;
        private String imageUrl;
        private String nftTitle;

        public static ResponseByCollection of(Nft nft) {
            return ResponseByCollection.builder()
                    .userId(nft.getUser().getId())
                    .nfTId(nft.getId())
                    .imageUrl(nft.getImageUrl())
                    .nftTitle(nft.getTitle())
                    .build();
        }
    }
}
