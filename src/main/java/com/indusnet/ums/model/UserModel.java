package com.indusnet.ums.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	
	private String id;
	private String name;
	
	@NotBlank(message = "Email cannot be empty")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	private String password;
	
	private Long dateCreated;
}
