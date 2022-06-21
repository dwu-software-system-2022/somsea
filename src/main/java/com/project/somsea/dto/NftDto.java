package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class NftDto {

    @Setter
    @Getter
    @Builder
    @ToString
    public static class Request {
        private Long collectionId;
        private String title;
        private String desc;
        private String imageUrl;
        private MultipartFile imageFile;
        private List<Long> partIds;

        public Nft toEntity(User user, Collection collection) {
            return Nft.builder()
                    .title(title)
                    .desc(desc)
                    .imageUrl(imageUrl)
                    .collection(collection)
                    .user(user)
                    .build();
        }

        public static Request newInstance() {
            return Request.builder().build();
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
        private String desc;
        private String imageUrl;
        private String token;
        private Nft.TokenStandard tokenStandard;
        private Nft.BlockChain blockChain;
        private Long userId;

        public static ResponseDetail of(Nft nft) {
            return ResponseDetail.builder()
                    .nftId(nft.getId())
                    .title(nft.getTitle())
                    .desc(nft.getDesc())
                    .imageUrl(nft.getImageUrl())
                    .token(nft.getToken())
                    .tokenStandard(nft.getTokenStandard())
                    .blockChain(nft.getBlockChain())
                    .userId(nft.getUser().getId())
                    .build();
        }
    }
}
