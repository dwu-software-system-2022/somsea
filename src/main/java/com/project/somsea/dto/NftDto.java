package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    public static class Response {
        // UserId, NftId, ImageUrl, NftTitle
        private Long userId;
        private Long nftId;
        private String imageUrl;
        private String nftTitle;

        public static Response of(Nft nft) {
            return Response.builder()
                    .userId(nft.getUser().getId())
                    .nftId(nft.getId())
                    .imageUrl(nft.getImageUrl())
                    .nftTitle(nft.getTitle())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ResponseDetail {
        private Long nftId;
        private String title;
        private String imageUrl;
        private String token;
        private String contractAddress;
        private String tokenStandard;
        private String blockChain;
        private Long userId;

        public static ResponseDetail of(Nft nft) {
            return ResponseDetail.builder()
                    .nftId(nft.getId())
                    .title(nft.getTitle())
                    .imageUrl(nft.getImageUrl())
                    .token(nft.getToken())
                    .contractAddress(nft.getContractAddress())
                    .tokenStandard(nft.getTokenStandard())
                    .blockChain(nft.getBlockChain())
                    .userId(nft.getUser().getId())
                    .build();
        }
    }
}
