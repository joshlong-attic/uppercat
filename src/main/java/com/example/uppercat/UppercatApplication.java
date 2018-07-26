package com.example.uppercat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@SpringBootApplication
public class UppercatApplication {

		private final byte[] bytes;
		private final Log log = LogFactory.getLog(getClass());
		private final Map<String, Object> result = new ConcurrentHashMap<>();

		public UppercatApplication(@Value("classpath:lion.jpg") Resource resource) throws IOException {
				this.log.info("resource : " + resource.exists() + " and " + resource.getURI().toString());
				this.bytes = StreamUtils.copyToByteArray(resource.getInputStream());

				this.result.put("cat", new String(Base64.getEncoder().encode(bytes)));
				this.result.put("key", "value");
		}

		@Bean
		Function<String, Map<String, Object>> uppercat() throws IOException {
				return in -> result;
		}

		public static void main(String[] args) {
				SpringApplication.run(UppercatApplication.class, args);
		}
}
