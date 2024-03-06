package com.instabasic.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.instabasic.backend.model.Profile;
import com.instabasic.backend.model.User;
import com.instabasic.backend.service.ProfileService;
import com.instabasic.backend.service.UserService;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	private UserService userService;
	@Autowired
	private ProfileService profileService;

	@Test
	void contextLoads() {
	}

	@Test
	void Test1() {
		User user1 = new User("user1", "user1@example.com", "password1");
		User user2 = new User("user2", "user2@example.com", "password2");
		// TODO:test
		// userService.registerUser(user1);
		// userService.save(user2);

		Profile profile1 = new Profile();
		profile1.setProfilename("profile1");
		profile1.setFirstName("nome1");
		profile1.setLastName("cognome1");
		profile1.setBirthday(LocalDate.of(1990, 5, 15));
		profile1.setBio("Sample bio for profile1");
		profile1.setAvatarUrl("https://example.com/avatar1.jpg");

		Profile profile2 = new Profile();
		profile2.setProfilename("profile2");
		profile2.setFirstName("nome2");
		profile2.setLastName("cognome2");
		profile2.setBirthday(LocalDate.of(1995, 8, 20));
		profile2.setBio("Sample bio for profile2");
		profile2.setAvatarUrl("https://example.com/avatar2.jpg");

		profile1.setUser(user1);
		profile2.setUser(user2);

		profileService.save(profile1);
		profileService.save(profile2);

		// fare test
		User foundUser1 = userService.findById(user1.getId());
		assertNotNull(foundUser1);
		assertEquals(user1.getUsername(), foundUser1.getUsername());

		// delete
		profileService.delete(profile1.getId());
		profileService.delete(profile2.getId());

		userService.delete(user1.getId());
		userService.delete(user2.getId());
	}

	@Test
	void uploadFileTest() {

	}

}
