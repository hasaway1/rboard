package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class PostService {
  @Autowired
  private PostDao postDao;
  @Autowired
  private CommentDao commentDao;
  @Autowired
  private PostMemberGoodDao postMemberGoodDao;
  private static final int BLOCK_SIZE = 5;

  public PostDto.Pages findAll(int pageno, int pagesize) {
    int totalcount = postDao.count();
    List<Post> posts = postDao.findAll(pageno, pagesize);
    return RBoardUtil.getPages(pageno, pagesize, BLOCK_SIZE, totalcount, posts);
  }

  public PostDto.Read findByPno(int pno, String loginId) {
    // Consumer : 입력은 있고, 출력은 없다
    // Supplier : 입력은 없고, 출력은 있다 -> 예외를 발생
    PostDto.Read post = postDao.findByPnoWithComments(pno).orElseThrow(()->new EntityNotFoundException("글을 찾을 수 없습니다"));
    if(loginId!=null && !post.getWriter().equals(loginId)) {
      postDao.increaseReadCnt(pno);
      post.setReadCnt(post.getReadCnt()+1);
    }
    return post;
  }

  public Post write(PostDto.Write dto, String loginId) {
    Post post = dto.toEntity(loginId);
    postDao.save(post);
    return post;
  }

  public void update(PostDto.Update dto, String loginId) {
    // ex) 내용을 바꾸는 경우라서 dto의 title이 비어있다면 -> update를 수행하면 title이 지워질 텐데?
    // 그러니까 dto의 title, content에 @NotEmtpty를 걸자
    // 그러면 사용자가 변경할 때 반드시 제목, 내용을 모두 입력해야 하는거야?
    // 읽기 화면에 출력했던 제목과 내용을 다시 받아오자..변경한 항목은 변경한 대로, 변경하지 않은 항목은 기존 내용을 받아오자

    // 글을 읽어서 글이 있다면 예외
    Post post = postDao.findByPno(dto.getPno()).orElseThrow(()->new EntityNotFoundException("글을 찾을 수 없습니다"));
    // 글을 변경하려는 사용자가 글쓴이가 아니라면 예외
    if(!post.getWriter().equals(loginId))
      throw new JobFailException("잘못된 작업있니다");
    postDao.update(dto);
  }

  public void delete(Integer pno, String loginId) {
    Post post = postDao.findByPno(pno).orElseThrow(()->new EntityNotFoundException("글을 찾을 수 없습니다"));
    if(!post.getWriter().equals(loginId))
      throw new JobFailException("잘못된 작업있니다");
    postDao.delete(pno);
  }

  public int good(int pno, String loginId) {
    // 비로그인이면 추천할 수 없다 -> @PreAuthrize()로 필터링되서 여기까지 안온다(X)
    // 자기가 작성한 글은 추천할 수 없다
    // 자기가 작성하지 않은 글은 추천할 수 있다

    // 1. 글이 없으면 예외처리
    // 2. 자기가 작성한 글이면 예외처리
    // 3. 이미 추천한 글이면 예외 처리
    // 4. 추천하지 않은 글이면 추천 후 새로운 추천수를 리턴
    Post post = postDao.findByPno(pno).orElseThrow(()->new EntityNotFoundException("글을 찾을 수 없습니다"));
    if(post.getWriter().equals(loginId))
      throw new JobFailException("자신의 글은 추천할 수 없습니다");
    boolean 추천했니 = postMemberGoodDao.existsByPnoAndUsername(pno, loginId);
    if(추천했니)
      throw new JobFailException("이미 추천했습니다");
    postMemberGoodDao.save(pno, loginId);
    postDao.increaseGoodCnt(pno);
    return postDao.findGoodCntByPno(pno).get();
  }
}












