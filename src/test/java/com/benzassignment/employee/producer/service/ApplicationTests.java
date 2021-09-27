package com.benzassignment.employee.producer.service;

import okhttp3.mockwebserver.MockWebServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests {
	MockProducer<String, String> producer;

	MockWebServer mockWebServer;
	HttpUriRequest request;

	String topic = "test_topic",id="csv2_20-08-2111";
	private String testCsvBody;

	@BeforeTestExecution
	public void setUp() throws IOException {
		producer = new MockProducer<String, String>(
				true, new StringSerializer(), new StringSerializer());


	}
	@Test
	void readFile() throws IOException {
		request = new HttpGet( "http://localhost:9001/file/read/"+id);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		assertEquals(200, httpResponse.getStatusLine().getStatusCode(), "Request Failed");
	}
	@Test
	void saveCSVFile() throws IOException {
		testCsvBody = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/csvTest1.json")));
		StringEntity stringEntity = new StringEntity(testCsvBody);
		HttpPost postRequest = new HttpPost( "http://localhost:9001/file/store/");
		postRequest.setHeader("FileType","CSV");
		postRequest.setHeader("Content-type", "application/json");
		postRequest.getRequestLine();
		postRequest.setEntity(stringEntity);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( postRequest );
		assertEquals(200, httpResponse.getStatusLine().getStatusCode(), "Request Failed");
	}

	@Test
	void saveXMLFile() throws IOException {
		testCsvBody = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/xmlTest1.json")));
		StringEntity stringEntity = new StringEntity(testCsvBody);
		HttpPost postRequest = new HttpPost( "http://localhost:9001/file/store/");
		postRequest.setHeader("FileType","XML");
		postRequest.setHeader("Content-type", "application/json");
		postRequest.getRequestLine();
		postRequest.setEntity(stringEntity);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( postRequest );
		assertEquals(200, httpResponse.getStatusLine().getStatusCode(), "Request Failed");
	}
	@Test
	void updateCSVFile() throws IOException {
		testCsvBody = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/csvTest2.json")));
		StringEntity stringEntity = new StringEntity(testCsvBody);
		HttpPost postRequest = new HttpPost( "http://localhost:9001/file/update");
		postRequest.setHeader("FileType","CSV");
		postRequest.setHeader("Content-type", "application/json");
		postRequest.getRequestLine();
		postRequest.setEntity(stringEntity);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( postRequest );
		assertEquals(200, httpResponse.getStatusLine().getStatusCode(), "Request Failed");
	}

	@Test
	void updateXMLFile() throws IOException {
		testCsvBody = new String(Files.readAllBytes(Paths.get("./src/test/java/resources/xmlTest2.json")));
		StringEntity stringEntity = new StringEntity(testCsvBody);
		HttpPost postRequest = new HttpPost( "http://localhost:9001/file/update");
		postRequest.setHeader("FileType","XML");
		postRequest.setHeader("Content-type", "application/json");
		postRequest.getRequestLine();
		postRequest.setEntity(stringEntity);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( postRequest );
		assertEquals(200, httpResponse.getStatusLine().getStatusCode(), "Request Failed");
	}
}
