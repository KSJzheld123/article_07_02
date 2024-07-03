package org.koreait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SignUp {

    int loginLastId = 0;
    int memberStatus = 0;
    Member loginStatusMember = null;
    int signUpStatus = 0;

    public SignUp() {
        System.out.println("테스트 아이디가 생성되었습니다");
        for (int i = 0; i < 3; i++) {
            int id = loginLastId + 1;
            String regTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String login2Id = "testId" + (i + 1);
            String login2Pw = "123";
            String name = (i + 1) + "번 테스트 계정";
            Member member = new Member(id, regTime, login2Id, login2Pw, name);
            members.add(member);
            loginLastId++;
        }
    }

    List<Member> members = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int idCount = 0;
    String login2Id = "";
    String login2Pw = "";
    String login2PwCheck = "";

    String head = "";

    void run2(int loginStatus) {
        memberStatus = loginStatus;
        signUpStatus = 0;
        run();
    }

    void run() {
        System.out.println("회원가입 및 로그인");
        while (memberStatus == 0 || memberStatus == 2 && signUpStatus == 0) {
            System.out.println("register or login");
            System.out.printf("명령어 ) ");
            String cmd = sc.nextLine().trim();
            String[] cmds = cmd.split(" ");

            if (cmds[0].equals("status")) {
                memberStatus();
            }

            if (cmds[0].equals("exit")) {
                exit();
            }

            if (cmd.length() == 0) {
                System.out.println("명령어를 입력해주세요");
                run();
            }

            if (cmds.length == 1) {
                head = cmds[0];
            } else {
                System.out.println("다시 입력해주세요");
                run();
            }

            if (head.equals("register")) {
                register();
            } else if (head.equals("login")) {
                login();
            }

        }

    }

    public void register() {

        loginStatus();

        String regTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        System.out.printf("닉네임 : ");
        String login2Name = sc.nextLine();

        System.out.printf("회원가입할 ID : ");
        while (true) {
            idCount = 0;
            login2Id = sc.nextLine();
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).loginId.equals(login2Id)) {
                    System.out.println("중복된 ID입니다");
                    idCount++;
                }
            }

            if (idCount == 0) {
                break;
            }
        }

        while (true) {
            System.out.printf("회원가입할 PW : ");
            login2Pw = sc.nextLine();
            System.out.printf("비밀번호 확인 : ");
            login2PwCheck = sc.nextLine();
            if (login2Pw.equals(login2PwCheck)) {
                break;
            }
            System.out.println("비밀번호를 다시확인해주세요");
        }

        if (login2Pw.equals(login2PwCheck)) {
            int id = loginLastId + 1;
            Member member = new Member(id, regTime, login2Id, login2Pw, login2Name);
            members.add(member);
            System.out.println("회원가입이 완료되었습니다");
            System.out.printf("ID번호 : %d , 닉네임 : %s \n", id, login2Name);
            loginLastId++;
            run();
        } else {
            System.out.println("비밀번호를 확인해주세요");
            register();
        }
    }

    public void login() {

        loginStatus();

        int count = 0;
        System.out.println("로그인");
        System.out.printf("ID : ");
        String iD = sc.nextLine();
        System.out.printf("PW : ");
        String pW = sc.nextLine();

        for (int i = 0; i < members.size(); i++) {
            if (iD.equals(members.get(i).loginId) && pW.equals(members.get(i).loginPw)) {
                System.out.println("로그인되었습니다");
                memberStatus = 2;
                loginStatusMember = members.get(i);
                count++;
            }
        }

        if (count == 1) {
            run();
        }

        if (count == 0) {
            System.out.println("다시 로그인 해주세요");
            login();
        }

    }

    public void memberStatus() {
        System.out.println("==============================================");
        for (int i = 0; i < members.size(); i++) {
            System.out.println("번호 : " + members.get(i).id);
            System.out.println("아이디 : " + members.get(i).loginId);
            System.out.println("비밀번호 : " + members.get(i).loginPw);
            System.out.println("닉네임 : " + members.get(i).name);
            System.out.println("가입날짜 : " + members.get(i).regDate);
            System.out.println("==============================================");
        }

    }

    public void exit() {
        signUpStatus = 1;
    }

    public void loginStatus() {
        if (memberStatus == 2) {
            System.out.println("로그아웃 후 이용해주세요");
            run();
        }
    }

}
