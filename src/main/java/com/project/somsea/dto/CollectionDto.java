package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CollectionDto {
		@Setter
		@Getter
		@Builder
		public static class Request {
			private Long id;
			private String name;
			private String url;
			private String logoImgUrl;
			private String description;
			private Nft nft;

		public Collection toEntity() {
			return Collection.builder()
					.id(id)
					.name(name)	
					.url(url)
					.logoImgUrl(logoImgUrl)
					.description(description)
					.build();
		}
		
		public static Request newInstance() {
			return Request.builder().build();
		}
	}
}