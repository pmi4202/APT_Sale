package com.ssafy.final_project.model.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.final_project.model.dto.BoardDto;
import com.ssafy.final_project.model.dto.CommentDto;
import com.ssafy.final_project.model.dto.FileDto;
import com.ssafy.final_project.model.dto.ListParameterDto;
import com.ssafy.final_project.model.mapper.BoardMapper;
import com.ssafy.final_project.model.mapper.CommentMapper;
import com.ssafy.util.PageNavi;
import com.ssafy.util.PathManager;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;
	private CommentMapper commentMapper;
	
	public BoardServiceImpl(BoardMapper boardMapper, CommentMapper commentDto) {
		this.boardMapper = boardMapper;
		this.commentMapper = commentDto;
	}

	@Override
	public int deleteComment(CommentDto dto) throws Exception {

		return commentMapper.deleteComment(dto);

	}

	@Override
	public List<CommentDto> selectCommentByBoadrNo(int no) throws Exception {
		List<CommentDto> list = commentMapper.selectComment(no);

		return list;
	}

	@Override
	public int createComment(CommentDto dto) throws Exception {
		return commentMapper.createComment(dto);
	}

	@Override
	public List<BoardDto> selectBoard(ListParameterDto lpd) throws Exception {
		
		return boardMapper.selectBoard(lpd);
	}

	@Override
	public int insertBoard(BoardDto dto) throws Exception {

		return boardMapper.insertBoard(dto);
	}

	@Override
	public int deleteBoard(Map<String, String> paramMap) throws Exception {

		return boardMapper.deleteBoard(paramMap);
	}
	
	
	
	

	@Override
	public int updateBoard(BoardDto dto) throws Exception {

		return boardMapper.updateBoard(dto);

	}

	@Override
	public BoardDto selectOne(int no) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectOne(no);
	}

	@Override
	public PageNavi getNaviAttribute(ListParameterDto lpd) throws Exception {
		
		int navSize = 5; // 페이지 검색바 사이즈
		
		// 페이지에 보여줄 레코드 갯수 , 현재 페이지 , 키워드,
		int totalCount = boardMapper.getBoardCount(lpd); // 총 글 갯수

		PageNavi pageNavi = new PageNavi();

		int totalPageNum = (totalCount - 1) / lpd.getItemPerPage() + 1;
		//총 페이지 갯수 =  (전체 레코드 갯수 -1) / 페이지당 갯수 + 1
		
		pageNavi.setStartPageNum((lpd.getCurPageNum() - 1) / navSize * navSize + 1);
		// navigation에서 보여줄 페이지 첫번쟤 번호
		if(pageNavi.getStartPageNum() < 1) {
			pageNavi.setStartPageNum(1);
		}
		
		int endPageNum = pageNavi.getStartPageNum() + navSize - 1;
		
		if (endPageNum > totalPageNum) {
			endPageNum = totalPageNum;
		}
		pageNavi.setEndPageNum(endPageNum);

		pageNavi.setCurPageNum(lpd.getCurPageNum());
		pageNavi.setHeadPageNum(1); // <<
		pageNavi.setTailPageNum(totalPageNum);// >>
		pageNavi.setPrevPageNum(pageNavi.getCurPageNum() <= navSize ? 1 : pageNavi.getStartPageNum() - 1);// <
		pageNavi.setNextPageNum(
				pageNavi.getCurPageNum() >= (totalPageNum - 1) / navSize * navSize + 1 ? totalPageNum : pageNavi.getEndPageNum() + 1);
		// >
		return pageNavi;
	}

	@Override
	@Transactional
	public int insertBoard(BoardDto dto, MultipartFile[] files, String path) throws Exception {
		String saveFolder = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		
		File fileDirectory = new File( PathManager.TEST_UPLOAD_PATH , "upload"+File.separator + saveFolder);

		//if(1==1.0) {
		//	return 1;
		//}
		// ex ) root > resources > upload > saverFolder>{file1,file2,file3}

		if (!fileDirectory.exists()) { // root(webapp) > resources > upload
			// 디렉토리가 존재하지 않으면
			if (!fileDirectory.mkdirs()) {
				throw new Exception("내부 서버 에러 : 파일 처리 과정 중 오류");
			}
		}
		
		
		String systemPath = fileDirectory.getCanonicalPath();
		//
				
		List<FileDto> fileDtoList = new ArrayList<FileDto>();
		FileDto fileDto ;
		for (MultipartFile mulFile : files) {
			String originalFileName = mulFile.getOriginalFilename();
			String saveFileName = UUID.randomUUID().toString()
					+ originalFileName.substring(originalFileName.lastIndexOf('.'));
			fileDto = new FileDto();
			fileDto.setOriginalFileName(originalFileName);
			fileDto.setSaveFileName(saveFileName);
			fileDto.setSaveFolder(saveFolder);
			
			mulFile.transferTo(new File(systemPath,saveFileName));
			System.out.println(systemPath);
			fileDtoList.add(fileDto);
		}
		
		//파일 저장하는데 오류가 난다면 db에 적용이 안될 꺼임...
		//따라서 위 파일 저장은 방어적 코드가 있어야한다. 
		// 파일이 여러개 일때 , 도중에 실패한다면 , 이미 저장된 파일은 삭제해야할 것이다. 귀찮....
		
		int result = 0;
		result = boardMapper.insertBoard(dto);
		dto.setFilelist(fileDtoList);
		//dto에 는 원래 no값을 db가 결정해주는데 mybatis selectKey 엘레먼트를 사용해서 dto에 넣어줌 
		if(result == 0 || boardMapper.insertFile(dto) == 0) {
			return 0;
		}

		return 1;
	}






}
