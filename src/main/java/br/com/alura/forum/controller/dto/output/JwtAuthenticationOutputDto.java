package br.com.alura.forum.controller.dto.output;

public class JwtAuthenticationOutputDto {

	private String token;
	private String tokenType = "Bearer";

	public JwtAuthenticationOutputDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getTokenType() {
		return tokenType;
	}

}
