package org.zerock.domain;

import lombok.Data;
import lombok.ToString;

// 밑의  Data로 테스트
//@Getter
//@Setter
//@ToString
//public class SampleVO {
//		
//	private String val1;
//	private String val2;
//	private String val3;
//	
//}

@Data
@ToString(exclude= {"val3"})
public class SampleVO {
	
	private String val1;
	private String val2;
	private String val3;
	
}
