package br.com.exemplo.apresentacao;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.security.Credentials;

@Name("loginManagedBean")
public class LoginManagedBean {
	
	@In 
	private Credentials credentials;
	
	public boolean autenticar(){		
		if(credentials.getUsername().equalsIgnoreCase("admin")){
			return true;
		}
		else{
			return false;
		}
	}

}
