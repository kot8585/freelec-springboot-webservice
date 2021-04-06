package com.jujuidu.book.springboot.domain.posts;

import com.jujuidu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//주요 어노테션을 클래스에 가깝게 두면 새 언어 전환으로 롬북이 더이상 필요 없을 경우 쉽게 삭제할 수 있다.
@Getter
@NoArgsConstructor //기본 생성자 자동추가
@Entity //JPA의 어노테이션 //테이블과 링크될 클래스임을 나타낸다.
public class Posts extends BaseTimeEntity {

    @Id //해당 테이블의 PK필드를 나타낸다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타낸다.
    private Long id;

    //테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다. //
    //사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성 //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
