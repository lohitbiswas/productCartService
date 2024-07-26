package com.indusnet.ums.common;




import org.springframework.http.HttpStatus;

import com.indusnet.ums.common.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ResponseModel extends CoreModel{
	
	private HttpStatus statusCode;
	private String traceId;
	private String messageEn;
	private String messageFr;
	private int messageTypeId;

}
