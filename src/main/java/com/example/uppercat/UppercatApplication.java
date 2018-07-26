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
import java.util.function.Function;

@SpringBootApplication
public class UppercatApplication {

		private final Log log = LogFactory.getLog(getClass());
		private final Resource resource;
		private final byte[] bytes;

		public UppercatApplication(@Value("classpath:lion.jpg") Resource resource) throws IOException {
				this.resource = resource;
				this.log.info("resource : " + resource.exists() + " and " + resource.getURI().toString());
				this.bytes = StreamUtils.copyToByteArray(resource.getInputStream());
		}

		@Bean
		Function<byte[], byte[]> uppercat() throws IOException {
				return in -> bytes;
		}

		public static void main(String[] args) {
				SpringApplication.run(UppercatApplication.class, args);
		}
}
