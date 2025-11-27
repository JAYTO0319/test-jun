package board.controller;

import java.util.Scanner;

import board.service.BoardService;
import board.vo.BoardVO;

public class BoardController {

	Scanner sc = new Scanner(System.in);
	BoardService service = new BoardService();

	public void start() {

		while (true) {
			System.out.println("========== 게시판 메뉴 ==========");
			System.out.println("1. 전체 조회");
			System.out.println("2. 글 등록");
			System.out.println("3. 글 상세 조회");
			System.out.println("4. 글 수정");
			System.out.println("5. 글 삭제");
			System.out.println("0. 종료");
			System.out.print("메뉴 선택 >> ");

			int menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1 -> selectAll();
			case 2 -> insert();
			case 3 -> detailSelect();
			case 4 -> update();
			case 5 -> delete();
			case 0 -> {
				System.out.println("프로그램을 종료합니다");
				return;
			}
			default -> System.out.println("잘못된 선택입니다.");
			}
		}
	}


	 private void update() {
			System.out.print("수정할 게시물의 ID를 입력하세요: ");
		    int id = Integer.parseInt(sc.nextLine());
			    
		    boolean isExist = service.configureID(id);
		    if(!isExist) {
		    	System.out.println("존재하지 않는 ID입니다.");
		    	return;
		    }
		    System.out.print("제목: ");
	        String title = sc.nextLine();
	        System.out.print("내용: ");
	        String content = sc.nextLine();
	        System.out.print("작성자: ");
	        String writer = sc.nextLine();

	        int result = service.update(id, title, content, writer);

	        if (result > 0) {
	            System.out.println("수정 성공!");
	        } else {
	            System.out.println("수정 실패!");
	        }
		    
		    
		}


	private void selectAll() {
        service.selectAll().forEach(vo -> {
            System.out.println(vo);
        });
    }

	private void insert() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();
        System.out.print("작성자: ");
        String writer = sc.nextLine();

        int result = service.insert(title, content, writer);

        if (result > 0) {
            System.out.println("등록 성공!");
        } else {
            System.out.println("등록 실패!");
        }
    }
    
	private void delete() {
    	System.out.println("삭제할 글 ID를 입력하세요:");
    	int boardId = Integer.parseInt(sc.nextLine());
    	
    	service.delete(boardId);
    	
    }
	
	private void detailSelect() {
        System.out.print("상세 조회할 글 ID를 입력하세요: ");
        int boardId = Integer.parseInt(sc.nextLine());

        BoardVO vo = service.detailSelect(boardId);

        if (vo == null) {
            System.out.println("해당 ID의 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("========== 글 상세 조회 ==========");
        System.out.println("ID    : " + vo.getBoardId());
        System.out.println("제목  : " + vo.getTitle());
        System.out.println("작성자: " + vo.getWriter());
        System.out.println("작성일: " + vo.getWriteDate());
        System.out.println("내용  : ");
        System.out.println(vo.getContent());
        System.out.println("==================================");
    }
}
