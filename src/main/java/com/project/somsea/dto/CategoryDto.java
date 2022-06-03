package main.java.com.project.somsea.dto;

import com.project.somsea.domain.Category;
import com.project.somsea.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class CategoryDto {
	@Setter
    @Getter
    @Builder
    public static class Request {
        private Long category_id;
        private String category_name;

        public Category toEntity(Tag tag) {
            return Category.builder()
                    .category_id(category_id)
                    .category_name(category_name)
                    .build();
        }
	}
}
