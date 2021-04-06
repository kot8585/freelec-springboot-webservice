package com.jujuidu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Posts 클래스로 Database를 접근하게 해줄 JpaRepository를 셍성합니다.
//JpaRepository 상속하면 기본적인 CRUD메소드가 자동을 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
