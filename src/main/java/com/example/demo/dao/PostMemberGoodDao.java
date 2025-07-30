package com.example.demo.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PostMemberGoodDao {
  @Select("select count(*) from posts_members_good where pno=#{pno} and username=#{loginId} and rownum=1")
  boolean existsByPnoAndUsername(int pno, String loginId);

  @Insert("insert into posts_members_good values(#{pno}, #{loginId})")
  int save(int pno, String loginId);
}
