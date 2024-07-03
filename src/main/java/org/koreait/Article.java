package org.koreait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Article {

    int loginStatus2 = 0;
    Member nowLoginMember = new Member();

    List<ArticleList> articles = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int lastId = 0;

    public Article() {
        System.out.println("테스트 게시물이 생성되었습니다");
        for (int i = 0; i < 3; i++) {
            String test = "테스트";
            int id = lastId + 1;
            String title = id + "번째 테스트 게시물 제목";
            String body = id + "번째 테스트 게시물 내용";
            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String time2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

            lastId++;

            ArticleList article = new ArticleList(title, body, id, time, time2, test);

            articles.add(article);
        }

    }

    String head = "";
    String body = "";
    int tail = 0;
    String search = "";
    String cmd = "";

    public void run2(int loginStatus, Member loginStatusMember) {
        loginStatus2 = loginStatus;
        nowLoginMember = loginStatusMember;
        run();
    }

    public void run() {
        System.out.println("게시판");
        System.out.printf("명령어 ) ");
        cmd = sc.nextLine().trim();
        rq();

        if (cmd.length() == 0) {
            System.out.println("명령어를 입력해주세요");
            run();
        }

        if(loginStatus2 == 0) {
            System.out.println("로그인 후 이용해주세요");

        }

        while (loginStatus2 == 2) {
            if (head.equals("exit")) {
                exit();
            } else if (head.equals("logout")) {
                logout();
            } else if (body.equals("write")) {
                write();
            } else if (body.equals("detail")) {
                detail();
            } else if (body.equals("delete")) {
                delete();
            } else if (body.equals("list")) {
                if (articles.size() == 0) {
                    System.out.println("게시글이 없습니다");
                    run();
                } else {
                    list();
                }
            } else if (body.equals("modify")) {
                modify();
            } else {
                System.out.println("명령어를 다시 입력해주세요");
                run();
            }
        }

    }

    public void write() {
        int id = lastId + 1;
        rq();
        System.out.printf("Title : ");
        String title = sc.nextLine();
        System.out.printf("Body : ");
        String body = sc.nextLine();
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String time2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());


        lastId++;

        ArticleList article = new ArticleList(title, body, id, time, time2, nowLoginMember.getName());
        System.out.printf("%d번 게시글이 작성되었습니다\n", id);

        articles.add(article);
        run();
    }

    public void detail() {
        int count = 0;
        rq();
        for (int i = 0; i < articles.size(); i++) {
            if (tail == 0) {
                System.out.println("검색할 id를 입력해주세요");
            } else if (tail == articles.get(i).id) {
                System.out.println(articles.get(i).id + "번글");
                System.out.println("최초작성시간 : " + articles.get(i).time);
                System.out.println("최근작성시간 : " + articles.get(i).time2);
                System.out.println("제목 : " + articles.get(i).title);
                System.out.println("내용 : " + articles.get(i).body);
                System.out.println("작성자 : " + nowLoginMember.getName());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("검색하신 게시글은 없습니다");
        }
        run();
    }

    public void delete() {
        int count = 0;
        rq();
        for (int i = 0; i < articles.size(); i++) {
            if (tail == articles.get(i).id) {
                System.out.println(articles.get(i).id + "번 게시글이 삭제되었습니다");
                articles.remove(i);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("삭제할 게시글이 없습니다");
        }
        run();
    }

    public void list() {
        int count = 0;
        rq();
        String searchTitle = search;
        System.out.println("   번호 /          최근작성날짜          /   작성자   /   제목    /   내용");
        if (searchTitle == "") {
            for (int i = 0; i < articles.size(); i++) {
                String titleCut = articles.get(i).title;
                String writerNameCut = articles.get(i).writerName;
                if (articles.get(i).title.length() > 3) {
                    titleCut = titleCut.substring(0, 3);
                }
                if (articles.get(i).writerName.length() > 3) {
                    writerNameCut = writerNameCut.substring(0, 3);
                }
                System.out.printf("   %d   /       %s       /   %s   /   %s   /   %s   \n", articles.get(i).id,articles.get(i).time2,writerNameCut , titleCut, articles.get(i).body);
            }
            run();
        } else if (searchTitle != "") {
            for (int i = 0; i < articles.size(); i++) {
                String articleTitleMatching = articles.get(i).title;
                if (articleTitleMatching.contains(searchTitle)) {
                    String titleCut = articles.get(i).title;
                    String writerNameCut = articles.get(i).writerName;
                    if (articles.get(i).title.length() > 3) {
                        titleCut = titleCut.substring(0, 3);
                    }
                    if (articles.get(i).writerName.length() > 3) {
                        writerNameCut = writerNameCut.substring(0, 3);
                    }
                    System.out.printf("   %d   /       %s       /  %s  /   %s   /   %s   \n", articles.get(i).id,articles.get(i).time2,writerNameCut , titleCut, articles.get(i).body);
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("검색된 결과가 없습니다");
            }
            run();
        }
    }

    public void modify() {
        int count = 0;
        rq();
        for (int i = 0; i < articles.size(); i++) {
            if (tail == articles.get(i).id) {
                System.out.println("수정 할 id : " + articles.get(i).id);
                System.out.println("수정 전 제목 : " + articles.get(i).title);
                System.out.println("수정 전 내용 : " + articles.get(i).body);
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");

                String body = sc.nextLine();
                articles.get(i).title = title;
                articles.get(i).body = body;
                String time2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                articles.get(i).time2 = time2;
                System.out.println(articles.get(i).id + "번글이 수정되었습니다");
                run();
            }
        }

        if (count == 0) {
            System.out.println("검색하신 게시글은 없습니다");
        }
        run();
    }

    void rq() {
        head = "";
        body = "";
        tail = 0;
        search = "";
        String[] cmds = cmd.split(" ");

        if (cmds.length == 1) {
            head = cmds[0];
        } else if (cmds.length == 2) {
            head = cmds[0];
            body = cmds[1];
        } else if (cmds.length == 3) {
            try {
                head = cmds[0];
                body = cmds[1];
                tail = Integer.parseInt(cmds[2]);
            } catch (NumberFormatException e) {
                System.out.println("검색할 id는 숫자만 입력해주세요");
                run();
            }
        }

        if (body.equals("list")) {
            if (cmds.length == 3) {
                search = cmds[2];
            }
        }
    }

    void exit() {
        rq();
        loginStatus2 = 0;
    }

    void logout() {
        rq();
        System.out.println("로그아웃 하였습니다");
        loginStatus2 = 0;
        nowLoginMember = null;
        run();
    }
}


