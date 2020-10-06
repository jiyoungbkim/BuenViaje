package edu.iot.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private int replyId;
	private int boardId;	
	private int level;
	private int parent;
	private String writer;
	private String password;
	private String content;
	private int deleted;
	private Date regDate;
	private Date updateDate;
}
