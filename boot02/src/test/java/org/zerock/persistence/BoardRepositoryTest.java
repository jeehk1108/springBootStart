package org.zerock.persistence;


import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testInsert() {
		
		Board board = new Board();
		board.setTitle("게시물의 제목");
		board.setContent("게시물의 내용 넣기.....");
		board.setWriter("user00");
		
		boardRepo.save(board);
		
	}
	
	
	@Test
	public void testRead() {
		
		boardRepo.findById(1L).ifPresent((board) -> {
			System.out.println("board 확인용 =====> " + board);
		});
	}
	
	
	// 책이랑 좀 상이함 https://memories95.tistory.com/137 참고
	@Test
	public void testUpdate() {
		
		System.out.println("Read First....................");
		Optional<Board> board = boardRepo.findById(1L);
		
		System.out.println("Update Title..................");
		board.get().setTitle("수정된 제목입니다.");
		
		System.out.println("Call Save()...................");
		boardRepo.save(board.get());
		
	}
	
	@Test
	public void testDelete() {
		
		System.out.println("DELETE Entity");
		
		boardRepo.deleteById(1L);
		boardRepo.deleteById(2L);
		boardRepo.deleteById(3L);
	}
	
	
	
	
	
	
	
}
