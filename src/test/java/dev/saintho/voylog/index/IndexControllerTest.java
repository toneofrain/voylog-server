package dev.saintho.voylog.index;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class})
@WebMvcTest
class IndexControllerTest {

	private final MockMvc mockMvc;

	public IndexControllerTest(@Autowired WebApplicationContext ctx,
		@Autowired RestDocumentationContextProvider contextProvider) {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
			.apply(documentationConfiguration(contextProvider))
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.alwaysDo(print())
			.build();
	}

	@DisplayName("RestDoc Swagger 연동 테스트")
	@Test
	void hello() throws Exception {

		mockMvc
			.perform(
			RestDocumentationRequestBuilders.get("/api/v1/index"))
			.andDo(
				MockMvcRestDocumentationWrapper.document("index-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint())
				))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
