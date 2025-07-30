package com.example.demo.dao;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import jakarta.validation.constraints.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface PostDao {
  int save(Post post);

  List<Post> findAll(int pageno, int pagesize);

  int count();

  int increaseReadCnt(int pno);

  Optional<Post> findByPno(int pno);

  Optional<PostDto.Read> findByPnoWithComments(int pno);

  @Update("update posts set title=#{title}, content=#{content} where pno=#{pno}")
  int update(PostDto.Update dto);

  @Delete("delete from posts where pno=#{pno}")
  int delete(Integer pno);

  @Select("select good_cnt from posts where pno=#{pno}")
  Optional<Integer> findGoodCntByPno(int pno);

  @Update("update posts set good_cnt=good_cnt+1 where pno=#{pno}")
  int increaseGoodCnt(int pno);
}








