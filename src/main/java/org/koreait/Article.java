package org.koreait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Article {

    int loginStatus = 0;

    List<ArticleList> articles = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int lastId = 0;

    public Article() {
        System.out.println("테스트 게시물이 생성되었습니다");
        for (int i = 0; i < 3; i++) {
            int id = lastId + 1;
            String title = id + "번째 테스트 게시물 제목";
            String body = id + "번째 테스트 게시물 내용";
            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String time2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

            lastId++;

            ArticleList article = new ArticleList(title, body, id, time, time2);

            articles.add(article);
        }
    }

    String head = "";
    String body = "";
    int tail = 0;
    String searchTitle = "";

    public void run() {
        System.out.printf("명령어 ) ");
        String cmd = sc.nextLine().trim();
        String[] cmds = cmd.split(" ");

        if(cmd.length() == 0) {
            System.out.println("명령어를 입력해주세요");
            run();
        }

        if (cmds.length == 2) {
            head = cmds[0];
            body = cmds[1];
        } else if (cmds.length == 3) {
            try {
                head = cmds[0];
                body = cmds[1];
                tail = Integer.parseInt(cmds[2]);
            } catch (NumberFormatException e) {
                if (body.equals("list")) {
                    searchTitle = cmds[2];
                    list();
                } else {
                    System.out.println("검색할 id는 숫자만 입력해주세요");
                    searchTitle = "";
                }
                run();
            }
        }

        if (body.equals("exit")) {
            sc.close();
        }

        while (loginStatus == 0) {
            if (body.equals("write")) {
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
            }
        }
    }

    public void write() {
        int id = lastId + 1;
        System.out.printf("Title : ");
        String title = sc.nextLine();
        System.out.printf("Body : ");
        String body = sc.nextLine();
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        ;
        String time2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        ;

        lastId++;

        ArticleList article = new ArticleList(title, body, id, time, time2);
        System.out.printf("%d번 게시글이 작성되었습니다\n", id);

        articles.add(article);
        run();
    }

    public void detail() {
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {
            if (tail == 0) {
                System.out.println("검색할 id를 입력해주세요");
            } else if (tail == articles.get(i).id) {
                System.out.println(articles.get(i).id + "번글");
                System.out.println("최초작성시간 : " + articles.get(i).time);
                System.out.println("최근작성시간 : " + articles.get(i).time2);
                System.out.println("제목 : " + articles.get(i).title);
                System.out.println("내용 : " + articles.get(i).body);
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
        for (int i = 0; i < articles.size(); i++) {
            if (tail == articles.get(i).id) {
                articles.remove(i);
                System.out.println("게시글이 삭제되었습니다");
                count++;
            }
        }

        if (count == 0) {
            System.out.println("삭제할 게시글이 없습니다");
        }
        run();
    }

    public void list() {

        System.out.println("   번호 /   제목  /   내용");
        if (searchTitle == "") {
            for (int i = 0; i < articles.size(); i++) {
                String titleCut = articles.get(i).title;
                if (articles.get(i).title.length() > 3) {
                    titleCut = titleCut.substring(0, 3);
                }
                System.out.printf("   %d   /   %s   /   %s   \n", articles.get(i).id, titleCut, articles.get(i).body);
            }
            run();
        } else if (searchTitle != "") {
            for (int i = 0; i < articles.size(); i++) {
                String articleTitleMatching = articles.get(i).title;
                if (articleTitleMatching.contains(searchTitle)) {
                    String titleCut = articles.get(i).title;
                    if (articles.get(i).title.length() > 3) {
                        titleCut = titleCut.substring(0, 3);
                    }
                    System.out.printf("   %d   /   %s   /   %s   \n", articles.get(i).id, titleCut, articles.get(i).body);
                }
            }
            run();
        }
    }

    public void modify() {
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {
            if (tail == articles.get(i).id) {
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

                run();
            }
        }

        if (count == 0) {
            System.out.println("검색하신 게시글은 없습니다");
        }
        run();
    }
}


