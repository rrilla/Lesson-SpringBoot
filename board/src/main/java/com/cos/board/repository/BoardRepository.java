package com.cos.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.board.model.Board;

//<관리하려는 타입, id의 타입>
//자동 IOC 등록됨. Y? JpaRepository 이놈이 가지고 있음.
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
