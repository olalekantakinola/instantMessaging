package com.creospan.app;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.creospan.app.entities.Authority;
import com.creospan.app.entities.User;
import com.creospan.app.repository.UserDetailsRepository;
@SpringBootApplication
public class SpringSecurityDemoAppApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoAppApplication.class, args);
	}
	@PostConstruct
	protected void init() {
		List<Authority> authorityList=new ArrayList<>();
		authorityList.add(createAuthority("USER","User role"));
		authorityList.add(createAuthority("ADMIN","Admin role"));
		User user=new User();
		user.setUserName("sachin");
		user.setFirstName("Sachin");
		user.setLastName("Tandan");
		user.setPassword(passwordEncoder.encode("sachin@123"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		userDetailsRepository.save(user);
	}
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
}
