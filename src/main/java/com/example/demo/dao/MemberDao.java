package com.example.demo.dao;

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface MemberDao {
  @Select("select count(*) from members where username=#{username}")
  boolean existsByUsername(String username);

  int save(Member member);

  @Select("select username from members where email=#{email} and rownum=1")
  Optional<String> findUsernameByEmail(String email);

  @Update("update members set profile=#{profile} where username=#{username}")
  int updateProfile(String profile, String username);

  @Update("update members set password=#{newPassword} where username=#{username}")
  int updatePassword(String username, String newPassword);

  @Select("select username, password, role, is_lock from members where username=#{username}")
  Optional<Member> loadLoginData(String username);

  @Select("select failed_attempts from members where username=#{username}")
  Optional<Integer> 로그인실패횟수(String username);

  @Update("update members set failed_attempts=failed_attempts+1 where username=#{username}")
  int 로그인실패횟수증가(String username);

  @Update("update members set is_lock=1 where username=#{username}")
  int 계정블록(String username);

  @Update("update members set failed_attempts=0 where username=#{username}")
  int reset로그인실패횟수(String loginId);

  @Select("select username, email, profile, join_day, levels from members where username=#{loginId}")
  Member findByUsername(String loginId);

  @Select("select password from members where username=#{loginId}")
  String findPasswordByUsername(String loginId);

  @Delete("delete from members where username=#{loginId}")
  int delete(String loginId);

  @Update("update members set is_lock=0, code=null where code=#{code} and rownum=1")
  int verifyAndActive(String code);
}








