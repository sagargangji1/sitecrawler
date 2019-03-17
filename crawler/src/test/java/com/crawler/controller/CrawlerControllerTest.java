package com.crawler.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.crawler.pojo.CrawlRequest;
import com.crawler.pojo.SiteMapResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CrawlerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	CrawlerController crawlerController;

	@Test
	public final void testCrawlerController() throws Exception {
		CrawlRequest request = new CrawlRequest();
		request.setUrl("http://redhat.com");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(request);
		this.mockMvc.perform(post("/crawler/crawl/site").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Success")));

		// test Invalid Number of Page
		request.setNoOfPagesToCrawl(100000);
		SiteMapResponse response = crawlerController.crawlPage(request);
		boolean isInvalidNoOfPages = response.getStatus().getCode() == 202;
		assertEquals(isInvalidNoOfPages, true);

		// test Invalid Domain of Page
		request.setUrl("redhat.com");
		response = crawlerController.crawlPage(request);
		boolean isInvalidDomain = response.getStatus().getCode() == 201;
		assertEquals(isInvalidDomain, true);

	}

}
