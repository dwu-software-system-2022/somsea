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

	@Getter
	@Builder
	public static class Response {
		private String name;
		private String userName;
		private Long collectionId;
		private String logoImgUrl;
		private String desc;

		public static CollectionDto.Response of(Collection collection) {
			return Response.builder()
					.name(collection.getName())
					.userName(collection.getUser().getName())
					.collectionId(collection.getId())
					.logoImgUrl(collection.getLogoImgUrl())
					.desc(collection.getDescription())
					.build();
		}
	}
}