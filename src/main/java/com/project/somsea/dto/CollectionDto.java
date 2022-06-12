package main.java.com.project.somsea.dto;

import main.java.com.project.somsea.domain.Category;
import main.java.com.project.somsea.domain.Tag;
import main.java.com.project.somsea.domain.Nft;
import main.java.com.project.somsea.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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

		public Collection toEntity(Nft nft, User user) {
			return Collection.builder()
					.collection_id(id)
					.user(user)
					.nftId(id);
					.build();
		}
	}
}
