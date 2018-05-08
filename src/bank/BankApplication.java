package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
    private static ArrayList<Account> accountList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
            System.out.println("--------------------------------------------------------------");
            System.out.print("선택> ");

            int selectNo = scanner.nextInt();
            if (selectNo == 1) {
                createAccount();            // 계좌생성하기
            } else if (selectNo == 2) {
                listAccounts();             // 계좌목록보여주기
            } else if (selectNo == 3) {
                deposit();                  // 예금하기
            } else if (selectNo == 4) {
                withdraw();                 // 출금하기
            } else if (selectNo == 5) {
                run = false;                // 종료
            }
        }
        System.out.println("프로그램 종료");
    }

    // 계좌 생성하기
    private static void createAccount() {
        System.out.println("------------");
        System.out.println("계좌생성");
        System.out.println("------------");
        System.out.print("계좌번호 : ");
        String ano = scanner.next();
        System.out.print("계좌주 : ");
        String owner = scanner.next();
        System.out.print("초기입금액 : ");
        int balance = scanner.nextInt();
        System.out.println("결과 : 계좌가 생성 되었씁니다.");
        accountList.add(new Account(ano, owner, balance));
    }

    // 계좌 목록보기
    private static void listAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("개설 된 계좌가 없습니다. 계좌를 신규 생성해주세요.");
            return;
        }
        System.out.println("------------");
        System.out.println("계좌목록");

        // accountList에 저장된 Account객체 목록을 출력합니다.
        for (Account account : accountList) {
            System.out.println(account.getAno() + "\t" + account.getOwner() + "\t" + account.getBalance());
        }
    }

    // 예금하기
    private static void deposit() {
        System.out.println("------------");
        System.out.println("예금");
        System.out.println("------------");
        System.out.print("계좌번호 : ");

        // 계좌번호를 통해 accountList에 저장된 Account객체를 찾습니다.
        Account account = findAccount(scanner.next());

        // 계좌번호와 일치하는 Account 객체가 없는 경우 예외처리를 합니다.
        if (account == null) {
            System.out.println("해당 계좌번호를 찾을 수 없습니다. 계좌번호를 다시 확인해주세요");
            return;
        }

        // 성공case 입니다. 성공시 예금액을 계좌번호로 찾은 Account객체 예금액을 입금합니다.
        System.out.print("예금액 : ");
        account.setBalance(scanner.nextInt() + account.getBalance());
        System.out.println("결과 : 예금이 성공되었습니다.");
    }

    // 출금하기
    private static void withdraw() {
        System.out.println("------------");
        System.out.println("출금");
        System.out.println("------------");
        System.out.print("계좌번호 : ");

        // 계좌번호를 통해 accountList에 저장된 Account객체를 찾습니다.
        Account account = findAccount(scanner.next());

        // 계좌번호와 일치하는 Account 객체가 없는 경우 예외처리를 합니다.
        if (account == null) {
            System.out.println("해당 계좌번호를 찾을 수 없습니다. 계좌번호를 다시 확인해주세요");
            return;
        }

        System.out.print("출금액 : ");
        int withdraw = scanner.nextInt();

        // 만약 출금액이 잔액보다 많은 경우 예외처리를 합니다.
        if (account.getBalance() -  withdraw< 0) {
            System.out.println("출금액이 잔액보다 많아 출금을 할 수 없습니다. 잔액을 확인해주세요.");
            return;
        }

        // 성공case 입니다. 성공시 출금액을 계좌번호로 찾은 Account객체 잔액에서 출금합니다.
        account.setBalance(account.getBalance() - withdraw);
        System.out.println("결과 : 출금이 성공되었습니다.");
    }

    // Account 배열에서 ano와 동일한 Account 객체 찾기
    private static Account findAccount(String ano) {
        for (Account account : accountList) {

            // acoountList에 저장된 Account 객체 중에서 입력된 계좌번호와 동일한 객체가 있는지 판단하여 반환합니다.
            if (account.getAno().equals(ano)) {
                return account;
            }
        }

        // 입력된 계좌번호(ano)와 동일한 객체가 없는 경우 null을 반환합니다.
        return null;
    }

}
