package com.concon.graphqlexample.model;



public class SigninPayload {
   private User user;
   private String accessToken;

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getAccessToken() {
      return accessToken;
   }

   public void setAccessToken(String accessToken) {
      this.accessToken = accessToken;
   }

   public SigninPayload(User user, String accessToken) {
      this.user = user;
      this.accessToken = accessToken;
   }
}
