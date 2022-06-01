package com.ssafy.final_project.model.service;

import java.util.List;

import com.ssafy.final_project.model.dto.DustDto;


public interface DustService {

	List<DustDto> getDustInfo(String siName) throws Exception;
}
