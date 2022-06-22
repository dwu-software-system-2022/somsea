package com.project.somsea.dto;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Part;
import com.project.somsea.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionDto {
		@Setter
		@Getter
		@Builder
		public static class Request {
			private String name;
			private String url;
			private String logoImgUrl;
			private MultipartFile imageFile;
			private String description;
			private String part;
			private List<Long> categoryIds;

		public Collection toEntity(User user) {
			return Collection.builder()
					.user(user)
					.name(name)
					.url(url)
					.logoImgUrl(logoImgUrl)
					.description(description)
					.build();
		}
		public static CollectionDto.Request of(Collection collection) {
			return CollectionDto.Request.builder()
					.name(collection.getName())
					.url(collection.getUrl())
					.logoImgUrl(collection.getLogoImgUrl())
					.description(collection.getDescription())
					.build();
		}
		public List<Part> generatePartEntities(Collection collection) {
			return Arrays.stream(part.split(","))
					.map(StringUtils::trim)
					.map(partName -> Part.builder().name(partName).collection(collection).build())
					.collect(Collectors.toList());
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