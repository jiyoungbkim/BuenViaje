package edu.iot.common.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Board {
	private long boardId;
	@NotEmpty(message="제목은 필수 항목입니다.")
	private String title;
	@NotEmpty(message="작성자는 필수 항목입니다.")
	private String writer;
	@NotEmpty(message="비밀번호는 필수 항목입니다.")
	private String password;
	private String content;
	private int readCnt;
	private List<Attachment> attachList;
	private Date regDate;
	private Date updateDate;
}
