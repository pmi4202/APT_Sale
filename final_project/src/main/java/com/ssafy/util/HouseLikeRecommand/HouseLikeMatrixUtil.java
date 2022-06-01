package com.ssafy.util.HouseLikeRecommand;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.final_project.model.dto.HouseLikeWithRowNumDto;

@Component
public class HouseLikeMatrixUtil {


	Comparator<TupleIntInt> comparator;
	
	public HouseLikeMatrixUtil(){
		comparator = new Comparator<TupleIntInt>() {
			

			@Override
			public int compare(TupleIntInt o1, TupleIntInt o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2.weight,o1.weight);
			}
		};
	}
	
	public int[][] toArray2D(List<HouseLikeWithRowNumDto> houseLikeList, int userCount, int maxAptCode) {
		//넘어온 리스트는 유저아이디 1순위, ROWnUM2 순위로 정렬되어잇따. (asc)
		
		int befor = 0; // rowNum이 증가하지 않으면 바뀐 것 . 
		
		int[][] map = new int[userCount][maxAptCode];
		int aptCode;
		int rowNum = 0;
		int boundIdx = 0;
		
		for(HouseLikeWithRowNumDto houseLike : houseLikeList) {
			aptCode = houseLike.getAptCode();
			rowNum = houseLike.getRowNum();
			
			if(rowNum <= befor ) {
				boundIdx++;
				befor = 0;
			}
			
			befor++;
			map[boundIdx][aptCode] = 1;
			
		}
		
		
		return map;
	}
	
	public int[][] transition(int[][] matrix){
		int oriRow,oriCol;
		oriRow = matrix.length;
		oriCol = matrix[0].length;
		int[][] temp = new int[oriCol][oriRow];
		
		for(int i = 0 ; i < oriRow; i ++) {
			for(int j = 0 ;  j < oriCol; j++) {
				temp[j][i] = matrix[i][j];
			}
		}
		
		
		return temp;
	}

	public LinkedList<TupleIntInt> getResult(int[][] leftOperand, int[][] tsMap) {
		
		int n = tsMap[0].length;
		int M = tsMap.length;
		
		int temp[] = new int[M];
		int[] result = new int[M];
		//(1xn) * (nxM행렬 )
		
		int weightVal = 0;
		int i,j;
		
		for( i = 0 ; i < n;i++) {
			for( j = 0 ; j < M;j++) {
				temp[j] = leftOperand[0][j] & tsMap[j][i];
				weightVal += temp[j];
			}
			
			for( j = 0 ; j < M;j++) {
				result[j] += tsMap[j][i] * weightVal; 
			}
			
			weightVal = 0;
		}
		
		LinkedList<TupleIntInt> aptCodes = new LinkedList<>();
		
		for(i=0 ; i < M;i++) {
			if(leftOperand[0][i] == 0 && result[i] != 0) {
				aptCodes.add(new TupleIntInt(i,result[i]));
			}
		}
		
		aptCodes.sort(comparator);
		
		return aptCodes;
	}

}
