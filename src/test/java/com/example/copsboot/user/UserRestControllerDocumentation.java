package com.example.copsboot.user;

import com.c4_soft.springaddons.security.oauth2.test.webmvc.AutoConfigureAddonsWebmvcResourceServerSecurity;
import com.example.copsboot.infraestructure.security.WebSecurityConfiguration;
import com.example.copsboot.user.web.UserRestController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@AutoConfigureAddonsWebmvcResourceServerSecurity
@Import(WebSecurityConfiguration.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
public class UserRestControllerDocumentation {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService service;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public RestDocsMockMvcConfigurationCustomizer
        restDocsMockMvcConfigurationCustomizer() {
            return configurer -> configurer.operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint(),
                            modifyHeaders().removeMatching("X.*")
                                    .removeMatching("Pragma")
                                    .removeMatching("Expires"));
        }
    }

    @Test
    public void ownUserDetailsWhenNotLoggedInExample() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(status().isUnauthorized())
                .andDo(document("own-details-unauthorized"));
    }
}