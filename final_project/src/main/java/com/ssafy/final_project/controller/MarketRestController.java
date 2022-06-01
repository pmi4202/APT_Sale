package com.ssafy.final_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.final_project.model.dto.MarketDto;
import com.ssafy.final_project.model.dto.SubCategoryDto;
import com.ssafy.final_project.model.service.MarketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="상권정보 api")
@RestController
@RequestMapping("/market")
public class MarketRestController {

	private MarketService marketService;
	
	public MarketRestController(MarketService marketService) {
		this.marketService = marketService;
	}
	
	@ApiOperation("동코드와 카테고리 코드를 통해 상권 정보를 가져온다. 카테고리 코드는 선택사항")
	@GetMapping("/{dongCode}/{categorycode}")
	public ResponseEntity<?> getMarget(@PathVariable("dongCode") String dongCode,
			@ApiParam("7자리") @PathVariable(value = "categorycode",required = false) String categoryCode
			) throws Exception{
		
		Map<String,String> map = new HashMap<>();
		map.put("dongCode",dongCode.substring(0, 8));
		
		if(categoryCode == null) {
			categoryCode="";
		}
		map.put("subcategoryCode",categoryCode.trim());
		
		List<MarketDto> markets =  marketService.selectMarkets(map);
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("markets",markets);
		result.put("cnt",markets.size());
		
		return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
		
	}
	
	@ApiOperation("업종 카테고리 정보")
	@GetMapping("/subcategory")
	public ResponseEntity<?> getCategory() throws Exception{

		List<SubCategoryDto> categories =  marketService.selectSubcategory();
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("category",categories);
		result.put("cnt",categories.size());
		
		return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
		
	}
}
