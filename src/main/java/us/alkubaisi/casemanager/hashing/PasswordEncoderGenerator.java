package us.alkubaisi.casemanager.hashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	 public static void main(String[] args) {

		String[] plainPasswords = {"admin1WeakPassword", "admin2WeakPassword", "worker1WeakPassword", "worker2WeakPassword", "supervisor1WeakPassword", "supervisor2WeakPassword",
				"worker3WeakPassword", "worker4WeakPassword","worker5WeakPassword","worker6WeakPassword","worker7WeakPassword"};
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		for(String password : plainPasswords){
			System.out.println(password + ":");
			System.out.println(passwordEncoder.encode(password));
		}

  }
}