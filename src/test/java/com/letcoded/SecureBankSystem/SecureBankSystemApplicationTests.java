package com.letcoded.SecureBankSystem;

import com.letcoded.SecureBankSystem.Repository.UserRepository;
import com.letcoded.SecureBankSystem.Service.user.UserService;
import com.letcoded.SecureBankSystem.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SecureBankSystemApplicationTests {

	@Test
	void contextLoads() {
	}

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void whenUsersPasswordIsLargerThan8Digit_thenSuccess(){

		UserEntity userEntity1 = new UserEntity();
		userEntity1.setName("HAYA");
		userEntity1.setPassword("52672782hhj");

		UserEntity userEntity2 = new UserEntity();
		userEntity2.setName("ahmad");
		userEntity2.setPassword("h2567yyhuuuj");

		UserEntity userEntity3 = new UserEntity();
		userEntity3.setName("saleh");
		userEntity3.setPassword("123");

		List<UserEntity> moksUserEntity = Arrays.asList(userEntity1, userEntity2, userEntity3);
		Mockito.when(userRepository.findAll()).thenReturn(moksUserEntity);

		List<String> userWithStrongPassword= userService.getAllUsersWithStrongPassword();

		List<String> expectedUsersWithStrongPassword = Arrays.asList("HAYA", "ahmad");
		assertEquals(expectedUsersWithStrongPassword,userWithStrongPassword);

	}

	@Test
	public void justtest(){

	}

}
