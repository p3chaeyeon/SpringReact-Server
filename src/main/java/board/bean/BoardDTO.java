package board.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Component
public class BoardDTO {
	@NonNull
    private int seq; // 글번호 9시퀀스 객체 seq_board_jsp 이용)
	@NonNull
	private String name;
	@NonNull
	private String id;
	private String email;
	@NonNull
	private String subject;
	@NonNull
	private String content;
	@NonNull
	private int ref; // 그룹번호
	@NonNull
	private int lev; // 단계
	@NonNull
	private int step; // 글순서
	private int pseq; // 원글번호; seq 와 똑같은 값이 들어감
	@NonNull
	private int reply; // 답변수
	private int hit; // 조회수
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone="Asia/Seoul") // Jackson 에서 제공
	private Date logtime;
}
