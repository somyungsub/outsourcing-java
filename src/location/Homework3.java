package location;

import java.util.Scanner;

public class Homework3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double lat [] = new double[100];
		double lon [] = new double[100];
		double score [] = new double[100];
		double min [] = new double[100];

		int a = 0;
		int b = 0;
		int c = 0;
		int i;
		loop : while(true)
		{
			System.out.println("Enter the command(Insert, Search, Print, Exit):");
			String name = scanner.next();
			if(name.equals("Insert")){
				System.out.print("Enter the latitude: ");
				lat[a] = scanner.nextInt();
				a++;
				System.out.print("Enter the lonitude: ");
				lon[b] = scanner.nextInt();
				b++;
				System.out.print("Enter the walking score: ");
				score[c] = scanner.nextInt();
				c++;
				System.out.println("Data insertion is completed!!");
				System.out.println("latitude = " + lat[a-1] + "," + " lonitude = " + lon[b-1] + "," + " walking score = " + score[c-1]);
				System.out.println("");
				continue loop;

			}

			if(name.equals("Print")){	
				for(i=0;i<a;i++) {
					System.out.println(i+1 + " data is stored"); 
					System.out.println("[" + i + "] latitude = " + lat[i] + ", lonitude = " + lon[i] + ", walking score = " + score[i]);
					System.out.println("");
				}
				continue loop;
			}

			if (name.equals("Search")) {
				System.out.println("Enter the latitude for search: ");
				int d = scanner.nextInt();
				System.out.print("Enter the lonitude for search: ");
				int e = scanner.nextInt();

				for (i = 0; i < a; i++) {
					min[i] = Math.sqrt((d - lat[i]) * (d - lat[i])+ (e - lon[i]) * (e - lon[i]));
					if (d == lat[i] && e == lon[i]) {   
						System.out.println("Search success!! walking score: " + score[i]);
						System.out.println("");
						continue loop;
					}
				}
				if (i == a) { 
					double minDistance = min[0];
					int indexOfMin = 0;       
					for (i = 1; i < a; i++) {
						if (minDistance > min[i]) {
							minDistance = min[i];
							indexOfMin = i;
						}
					}
					i= indexOfMin;
				}
				System.out.println("Search failed!! the walking score of the nearest position is as follows latitude = " + lat[i] + ", lonitude = " + lon[i] + ", walking score = " + score[i]);
				System.out.println("");
				continue loop;
			}

			if (name.equals("Exit")) {
				break;
			}
			else {
				System.out.println("wrong command");
				continue loop;
			}
		}
	}
}







