package com.cos.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.board.model.Board;

//<관리하려는 타입, id의 타입>
//자동 IOC 등록됨. Y? JpaRepository 이놈이 가지고 있음.
public interface BoardRepository extends JpaRepository<Board, Integer>{
	//인터페이스는 public abstract 생략되어있음
	
	//-------------NamingQuery------------
	
	//select * from board where title = ?1
	//Board findByTitle(String title);
	
	//select * from board where title = ?1 and content = ?2
	//Board findByTitleAndContent(String title, String content);
	
	//첫글자가 dd인 title 조회
	//Board findByTitleStartingWith(String dd);
	
	
	//-------------NativeQuery------------
	
	@Query(value = "select * from board where id = :id", nativeQuery = true)
	//nativeQuery 내가만든쿼리 실행할지말지 default가 false
	Board mFindById(int id);
	
	@Modifying	//수정,삭제,변경,저장 할 시 꼮! 적용 - 커밋시켜줌
	@Query(value = "delete from board where id = :id", nativeQuery = true)
	//int로 받으면 변경 행 갯수 리턴, -1은 에러
	int mDeleteById(int id);
	
	@Query(value = "select * from board where title like %:search%", nativeQuery = true)
	List<Board> mSearch(@Param("search")String search);
		
}
