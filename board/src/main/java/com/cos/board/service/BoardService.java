package com.cos.board.service;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.board.config.ex.MyArgsNotFound;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(BoardSaveRequestDto dto) {
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardRepository.save(boardEntity);
	}
	
	//acid 읽기인데도 왜 설정?
	//@Transactional(readOnly = true)
	public List<Board> 글목록() {
		return boardRepository.findAll();
	}
	
	@Transactional
	public Board 글상세보기(int id) throws MyArgsNotFound {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> 
					 new RuntimeException("id값 잘못옸다")
				);
		board.setReadCount(board.getReadCount()+1);
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
	
	public Board 테스트(String title) {
		return boardRepository.findByTitle(title);
	}
}
