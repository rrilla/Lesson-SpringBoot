package com.cos.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.board.config.auth.PrincipalDetails;
import com.cos.board.config.ex.MyArgsNotFound;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.dto.ImageSaveDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(BoardSaveRequestDto dto, PrincipalDetails princiql) {
		int n = dto.getContent().indexOf('"');	//첫번쨰 "의 위치 int
		int n2 = dto.getContent().indexOf('"',n+3);
		String image = dto.getContent().substring(n+1, n2);
		System.out.println(image);
		
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardEntity.setImage(image);
		boardEntity.setUser(princiql.getUser());
		boardRepository.save(boardEntity);
	}
	
	//acid? 읽기인데도 왜 설정?
	//@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	public List<Board> 글검색(String search, Pageable pageable){
		return boardRepository.mSearch(search);
	}
	
	@Transactional
	public Board 글상세보기(int id) throws MyArgsNotFound {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> 
					 new RuntimeException("id값 잘못옸다")
					 //new Exception("id값 잘 못 들어옴.")/
					 //new MyArgsNotFound("id잘못옴")
				);
		board.setReadCount(board.getReadCount()+1);	//조회수 증가
		return board;
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		//boardRepository.deleteById(id);
		boardRepository.mDeleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, BoardSaveRequestDto dto) {
		
		//Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		//boardEntity.setId(id);
		//boardRepository.save(boardEntity);
		//save할 때 id값이 db저장된 값과 동일할 시 자동으로 jpa에서 update실행
		//근데 수정값을 받을 때 Board의 모든 값을 다 받아야 함.
		
		//더티체킹
		//Board boardEntity = boardRepository.findById(id)
			//	.orElseThrow(()-> new RuntimeException("id값 잘못들어옴"));
		Board boardEntity = boardRepository.mFindById(id);
		boardEntity.setTitle(dto.getTitle());
		boardEntity.setContent(dto.getContent());
		
	}
	
	public ImageSaveDto 이미지저장(MultipartFile multipartFile) {
		ImageSaveDto dto = new ImageSaveDto();
		String fileRoot = "C:/Users/admin/Desktop/test/";	//학원,저장될 외부 파일 경로
		//String fileRoot = "C:/Users/user/Desktop/test/";	//집,저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		File targetFile = new File(fileRoot + savedFileName);	
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			dto.setUrl("/summernoteImage/"+savedFileName);
			dto.setResponseCode("success");
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			dto.setResponseCode("error");
			e.printStackTrace();
		}
		return dto;
	}
	
}
