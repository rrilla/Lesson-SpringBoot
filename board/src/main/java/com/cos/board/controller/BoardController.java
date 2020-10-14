package com.cos.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cos.board.config.ex.MyArgsNotFound;
import com.cos.board.dto.BoardSaveRequestDto;
import com.cos.board.model.Board;
import com.cos.board.model.UploadFile;
import com.cos.board.repository.BoardRepository;
import com.cos.board.service.BoardService;
import com.google.gson.JsonObject;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto dto) {
		
		boardService.글쓰기(dto);
		
		return "ok";
	}
	
	@GetMapping({"","/","/list"})	//중괄호 입력시 안에 여러주소 배열로 선언가능
	public String list(Model model) {
		model.addAttribute("boards", boardService.글목록());
		return "list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) throws Exception {
		//못찾으면 어떻게 할지 
		//Optional<Board> opBoard = boardRepository.findById(id);
		//Board board = boardRepository.findById(id).orElse(new Board());
		
//		Board board = boardRepository.findById(id).orElseGet(new Supplier<Board>() {
//			@Override
//			public Board get() {
//				System.out.println("id값의 데이터가 엄슴.");
//				return new Board();
//			}
//		});
		
//		Board board = boardRepository.findById(id).orElseGet(
//			() -> {
//				System.out.println("id값의 데이터가 엄슴.");
//				return new Board();
//			}
//		);
		
//		Board board = boardRepository.findById(id)
//				.orElseThrow(new Supplier<Exception>() {
//
//				@Override
//				public Exception get() {
//					return new Exception("id값 잘 못 들어옴.");
//				}
//			});
		model.addAttribute("board", boardService.글상세보기(id));
		return "detail";
	}
	
	@DeleteMapping("board/{id}")
	@ResponseBody
	public String delete(@PathVariable int id) {
		boardService.글삭제하기(id);
		return "ok";
	}
	
	@PutMapping("board/{id}")	//
	@ResponseBody
	public String update(@PathVariable int id, @RequestBody BoardSaveRequestDto dto) {
		boardService.글수정하기(id, dto);
		return "ok";
	}
	
	@GetMapping("test")
	@ResponseBody
	public Board test() {
		System.out.println(boardService.테스트("test"));
		return boardService.테스트("test");
	}
	
	@PostMapping(value="/image", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	
	

}
