package edu.miu.cs.cs544.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("user_id")
  private Integer userId;

  public AuthenticationResponse(String accessToken, Integer userId) {
    this.accessToken = accessToken;
    this.userId = userId;
  }
  public AuthenticationResponse(){}

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
