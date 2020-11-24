package com.dy.memory.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class DiaryDto {

	@NotEmpty(message = "标题不能为空")
	private String diarytitle;

	private String diarycontent;

	public String getDiarytitle() {
		return diarytitle;
	}

	public void setDiarytitle(String diarytitle) {
		this.diarytitle = diarytitle;
	}

	public String getDiarycontent() {
		return diarycontent;
	}

	public void setDiarycontent(String diarycontent) {
		this.diarycontent = diarycontent;
	}
}
