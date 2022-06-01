package com.ssafy.util.dust;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.final_project.model.dto.DustDto;
import com.ssafy.util.Pair;

public class DustUtilProxy implements DustUtil {

	private DustUtil dustUtil;
	private Map<String,Pair<String, List<DustDto>>> map;
	private SimpleDateFormat foramt;
	private SimpleDateFormat foramt2;
	public DustUtilProxy(DustUtil dustUtil) {
		this.dustUtil = dustUtil;
		foramt = new SimpleDateFormat("yyyy-MM-dd HH");
		//date를 문자로 바꿀 때 사용 .. map에서는 이렇게 활용한다.
		foramt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//문자를 date로 바꿀 때 사용.. api에서는 이렇게 넘어온다. 
		map = new HashMap<String,Pair<String, List<DustDto>>>();
	}
	
	@Override
	public List<DustDto> getDustList(String siName) throws Exception {
		Date now = new Date();
		String nowString = foramt.format(now);
		
		Pair<String,List<DustDto>> temp;
		temp = map.get(siName);
		List<DustDto> result= null;
		if(temp != null) { //해당 시를 이미 탐색한 적 있다면
			
			if(nowString.equals(temp.getFirst())) {
				//년 월 일 시간, 까지 같다면 ,,, 
				result = temp.getSecond();
				System.out.println("캐싱됨");
			}else {
				// 다르면 다시 불러와야함
				result = dustUtil.getDustList(siName);
				String newDataTime = result.get(0).getDataTime();
				String formatednewDataTime = this.foramt.format(this.foramt2.parse(newDataTime));
				// yyyy-mm-dd HH:MM 을 yyyy-mm-dd HH 로 바꾸기 위함
				temp = new Pair<String, List<DustDto>>(formatednewDataTime, result);
				map.put(siName, temp);
			}
		}else { //아예 탐색한 적이 없다면 
			result = dustUtil.getDustList(siName);
			String newDataTime = result.get(0).getDataTime();
			String formatednewDataTime = this.foramt.format(this.foramt2.parse(newDataTime));
			// yyyy-mm-dd HH:MM 을 yyyy-mm-dd HH 로 바꾸기 위함
			temp = new Pair<String, List<DustDto>>(formatednewDataTime, result);
			map.put(siName, temp);
		}
		
		
		return result;
	}

}
