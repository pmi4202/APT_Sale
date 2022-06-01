package com.ssafy.util.dust;

import java.util.List;

import com.ssafy.final_project.model.dto.DustDto;

public interface DustUtil {
	public List<DustDto> getDustList(String siName) throws Exception;
}
