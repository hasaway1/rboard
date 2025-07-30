package com.example.demo.service;

import com.example.demo.dao.CommentDao;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.exception.JobFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
  @Autowired
  private CommentDao commentDao;

  public List<Comment> write(CommentDto.Craete dto, String loginId) {
    commentDao.save(dto.toEntity(loginId));
    return commentDao.findByPno(dto.getPno());
  }

  public List<Comment> delete(CommentDto.Delete dto, String loginId) {
    boolean result = commentDao.deleteByCnoAndWriter(dto.getCno(), loginId)==1;
    if(!result)
      throw new JobFailException("댓글을 삭제하지 못했습니다");
    return commentDao.findByPno(dto.getPno());
  }
}
