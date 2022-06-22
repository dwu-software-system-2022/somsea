package com.project.somsea.dto;

import com.project.somsea.domain.Category;
import com.project.somsea.domain.Part;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor //파라미터 없는 기본 생성자 생
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Category toEntity() {
        return Category.builder()
                .id(id)
                .name(name)
                .build();
    }

    @Getter
    @Builder
    public static class Response {
        private Long id;
        private String name;

        public static CategoryDto.Response of(Category category) {
            return CategoryDto.Response.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }
    }

}
