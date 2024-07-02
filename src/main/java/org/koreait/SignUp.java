package org.koreait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SignUp {
    List<Member> members = new ArrayList<Member>();
    int loginLastId = 0;
    Scanner sc = new Scanner(System.in);
    int loginStatus = 0;
    String head = "";
    String body = "";

    void run() {
        while (loginStatus == 0) {
            System.out.printf("명령어 ) ");
            String cmd = sc.nextLine().trim();
            String[] cmds = cmd.split(" ");

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                run();
            }

            if (cmds.length == 2) {
                head = cmds[0];
                body = cmds[1];
            }

            if (body.equals("register")) {
                register();
            } else if (body.equals("login")) {
                login();
            }

        }

    }

    public void register() {
        String regTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        System.out.printf("닉네임 : ");
        String login2Name = sc.nextLine();

        System.out.printf("회원가입할 ID : ");
        String login2Id = sc.nextLine();
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).loginId.equals(login2Id)) {
                System.out.println("중복된 ID입니다");
                register();
            }
        }

        System.out.printf("회원가입할 PW : ");
        String login2Pw = sc.nextLine();
        System.out.printf("비밀번호 확인 : ");
        String login2PwCheck = sc.nextLine();

        if (login2Pw.equals(login2PwCheck)) {
            int id = loginLastId + 1;
            Member member = new Member(id, regTime, login2Id, login2Pw, login2Name);
            members.add(member);
            System.out.println("회원가입이 완료되었습니다");
            System.out.printf("ID번호 : %d , 닉네임 : %s \n", id, login2Name);
            loginLastId++;
        } else {
            System.out.println("비밀번호를 확인해주세요");
            register();
        }
    }

    public void login() {
        System.out.println("로그인");
        System.out.printf("ID : ");
        String iD = sc.nextLine();
        System.out.printf("PW : ");
        String pW = sc.nextLine();

        for (int i = 0; i < members.size(); i++) {
            if (iD.equals(members.get(i).loginId) && pW.equals(members.get(i).loginPw)) {
                System.out.println("로그인되었습니다");
                loginStatus = 1;
            } else {
                System.out.println("다시 로그인 해주세요");
                login();
            }
        }
    }
}
