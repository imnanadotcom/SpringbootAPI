package com.example.copsboot;

import com.example.copsboot.infraestructure.SpringProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(SpringProfiles.INTEGRATION_TEST)
class CopsbootApplicationTests {

	@Test
	void contextLoads() {
	}

}
