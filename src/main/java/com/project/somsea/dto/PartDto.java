package com.project.somsea.dto;

import com.project.somsea.domain.Part;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PartDto {

    @Getter
    @Builder
    public static class Request {
        private List<Long> partIds;

        public boolean partIdsIsEmpty() {
            return partIds.isEmpty();
        }
        public static Request newInstance() {
            return Request.builder().build();
        }
    }

    @Getter
    @Builder
    public static class Response {
        private Long partId;
        private String name;

        public static PartDto.Response of(Part part) {
            return Response.builder()
                    .partId(part.getId())
                    .name(part.getName())
                    .build();
        }
    }
}
