package com.bcdbook.summer.common.tools.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class IAuthenticator extends Authenticator {
	private String username;  
    private String password;  
    public IAuthenticator(){  
          
    }  
      
    public IAuthenticator(String username, String password) {  
        this.username = username;  
        this.password = password;  
    }  
  
    @Override  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication(this.getUsername(), this.getPassword());  
    }  
      
    public String getUsername() {  
        return username;  
    }  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
}
