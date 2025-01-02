package com.clone.backend.uber;

import com.clone.backend.uber.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberBackendCloneApplicationTests {

	@Autowired
	EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		String[] emails = {"cihihog208@gholar.com", "2041011036.rounakkhuntia@gmail.com"};
		emailSenderService.sendEmail(emails,
				"test email subject",
				"test email body");
	}

}
