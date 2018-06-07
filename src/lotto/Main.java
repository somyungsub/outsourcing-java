package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Integer> lotto = new HashSet<>();   // 로또 저장 자료구조 (Set)
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("1.직접입력 2. 자동생성 : ");
        int input = scanner.nextInt();

        // 입력판단
        while (true) {
            if (input == 1) {
                // 직접입력
                while (lotto.size() < 6) {
                    System.out.print("로또 숫자 입력 : ");
                    int lottoNum = scanner.nextInt();

                    // 1~45 이외 숫자 입력시 예외처리
                    if (lottoNum < 1 || lottoNum > 45) {
                        System.out.println("로또 숫자를 잘못 입력 했습니다. 재 입력 해주세요!!");
                        continue;
                    }

                    // 이미 앞에 입력한 숫자를 입력하였을 경우 예외처리
                    if (lotto.contains(lottoNum)) {
                        System.out.println("이미 입력된 동일한 숫자를 입력하였습니다. 재 입력 해주세요!!");
                        continue;
                    }
                    lotto.add(lottoNum); // 자동생성 후 저장
                }
                break;
            } else if (input == 2) {

                // 자동생성 - lotto 갯수가 6개가 될때 까지 반복
                while (lotto.size() < 6) {
                    lotto.add(random.nextInt(45) + 1); // 자동생성 후 저장
                }
                break;
            } else {
                // 오입력시 작업처리
                System.out.print("잘못입력했습니다. 다시입력 해주세요 \n 1.직접입력 2. 자동생성 : ");
                input = scanner.nextInt();
            }

        }

        // 입력된 숫자 or 자동생성된 숫자
        System.out.println("====== 입력된 숫자 or 자동생성된 숫자 ======");
        StringBuilder sb = new StringBuilder("입력번호 : ");
        for (Integer i : lotto) {
            sb.append(i).append(" ");
        }
        sb.append(System.lineSeparator());
        System.out.println(sb.toString());

        // 로또 추첨
        Set<Integer> lottoRaffle = new HashSet<>();
        while (lottoRaffle.size() < 7) {
            lottoRaffle.add(random.nextInt(45) + 1);
        }

        Integer[] list = lottoRaffle.toArray(new Integer[7]);
        StringBuilder sb2 = new StringBuilder("추첨번호 : ");
        System.out.println("================ 추첨번호 ===================");
        for (int i = 0; i < list.length - 1; i++) {
            sb2.append(list[i]).append(" ");
        }
        int bonusNum = list[list.length - 1];
        sb2.append(System.lineSeparator());
        sb2.append("보너스번호 : ").append(bonusNum);
        System.out.println(sb2.toString());

        // 맞은 갯수
        List<Integer> raffleList = new ArrayList<>();   // 맞은 숫자 저장 자료구조
        int count = 0;      // 당첨 갯수
        int countBonus = 0; // 보너스 당첨 갯수
        for (Integer lottoNumber : lotto) {
            for (int i = 0; i < list.length - 1; i++) {
                Integer raffle = list[i];
                // 입력수와 추첨수가 같은경우 count를 1증가
                if (raffle.compareTo(lottoNumber) == 0) {
                    count++;
                    raffleList.add(lottoNumber);
                }
            }

            // 보너스 숫자와 입력수 비교 후 일치하면 countBonus 1증가
            if (bonusNum == lottoNumber) {
                countBonus++;
            }
        }

        // 결과 출력
        System.out.println("================ 추첨결과 ===================");
        if (raffleList.isEmpty()) {
            System.out.println("당첨된 번호 없음!");
        } else {
            System.out.print("당첨번호 : ");
            for (Integer raffle : raffleList) {
                System.out.print(raffle +"  ");
            }
            System.out.println();
        }


        if (count == 6) {
            System.out.println("1등 당첨! *** 당첨금 : 100억원 ***");
        } else if (count == 5) {
            if (countBonus > 0) {
                System.out.println("보너스번호 : " + bonusNum);
                System.out.println("2등 당첨! *** 당첨금 : 1억원 ***");
            } else {
                System.out.println("3등 당첨! *** 당첨금 : 100만원 ***");
            }
        } else if (count == 4) {
            System.out.println("4등 당첨! *** 당첨금 : 5만원 ***");
        } else if (count == 3) {
            System.out.println("5등 당첨! *** 당첨금 : 5천원 ***");
        } else {
            System.out.println("꽝입니다~!!");
        }
    }
}
