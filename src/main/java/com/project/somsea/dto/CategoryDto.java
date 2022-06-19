package com.project.somsea.dto;

import com.project.somsea.domain.Category;
import com.project.somsea.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor //파라미터 없는 기본 생성자 생
public class CategoryDto {
        private Long id;
        private String name;

        public CategoryDto (Category entity) {
            this.id = entity.getId();
            this.name = entity.getName();
        }
        
        public Category toEntity() {
            return Category.builder()
                    .id(id)
                    .name(name)
                    .build();
        }
       
	
}
