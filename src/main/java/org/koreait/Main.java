package org.koreait;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Article article = new Article();
        SignUp signUp = new SignUp();
        int systemStatus = 0;
        Member loginMember = null;
        int loginStatus = 0;

        System.out.println("=== Article 실행 ===");

        while (systemStatus == 0) {

            System.out.println("n1 : 게시판 이동");
            System.out.println("n2 : 회원가입 및 로그인 이동");
            System.out.printf("명령어 ) ");
            String n = sc.nextLine();

            if(n.equals("exit")) {
                systemStatus = 1;
            }

            if(n.equals("n1")) {
                article.run2(loginStatus, loginMember);
                loginMember = article.nowLoginMember;
                loginStatus = article.loginStatus2;
            } else if(n.equals("n2")) {
                signUp.run2(loginStatus);
                loginMember  = signUp.loginStatusMember;
                loginStatus = signUp.memberStatus;
            }

        }
    }
}