package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	public List<Board> findBoardByTitle(String title);
	
	// findBy~  게시물에서 user00 이라는 작성자의 모든 데이터를 구하기
	public Collection<Board> findBoardByWriter(String writer);
	
	// like 연산자
	public Collection<Board> findByWriterContaining(String writer);
	
	// and 혹은 or 조건처리
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	// 부등호 처리    title LIKE % ? % AND BNO > ?
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
	
	// order by 처리    bno > ? ORDER BY bno DESC
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// 페이징 처리와 정렬     bno > ? ORDER BY bno DESC limit ?, ?
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	// Page<T> 타입
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
	
	// 단순 게시물의 처리를 위한 @Query 작성
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);
	
	// 내용에대한 검색 처리 - @Param
	@Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String content);
	
	// 작성자에 대한 검색 처리 - #{#entityName}
	@Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	List<Board> findByWriter(String writer);
	
	// JPQL을 사용하면 필요한 컬럼만 추출가능
	// content 칼럼의 내용을 제외한 칼럼을 가져오고 싶은경우
	@Query("SELECT b.bno, b.title, b.writer, b.regdate FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Object[]> findByTitle2(String title);
	
	// @Query와 Paging 처리/ 정렬
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findBypage(Pageable pageable);
	
	
	
	
	
	
	
	
	
}
