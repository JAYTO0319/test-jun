package board.controller;

import java.util.Scanner;

import board.service.BoardService;

public class BoardController {

    Scanner sc = new Scanner(System.in);
    BoardService service = new BoardService();

    public void start() {

        while (true) {
            System.out.println("========== 게시판 메뉴 ==========");
            System.out.println("1. 전체 조회");
            System.out.println("2. 글 등록");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택 >> ");

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1 -> selectAll();
                case 2 -> insert();
                case 4 -> update();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
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
}
