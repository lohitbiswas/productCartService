package com.indusnet.ums.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@ToString
@Builder
	public class LoggingResponseModel {
		private HttpStatus statusCode;
		private String traceId;
		private String message;
		private MessageTypeConst messageTypeId;
		private String companyId;
		private String tenantId;
		private Long dateCreated;
		private String userId;
	}

