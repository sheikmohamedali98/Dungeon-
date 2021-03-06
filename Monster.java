import java.util.Scanner;
import java.util.TreeSet;

public class Monster {

	static Scanner in = new Scanner(System.in);
	static TreeSet<Integer> steps = new TreeSet<Integer>();
	static int[] rowPath = { 0, 0, -1, 1 };
	static int[] colPath = { 1, -1, 0, 0 };
	static int row;
	static int col;

	public static void main(String[] args) {
		System.out.println("Enter the dungeon dimesions");
		row = in.nextInt();
		col = in.nextInt();
		System.out.println("Enter the Position of the adventurer");
		int startRow = in.nextInt();
		int startCol = in.nextInt();
		startRow--;
		startCol--;

		System.out.println("Enter the position of the monster");
		int monsterStartRow = in.nextInt();
		int monsterStartCol = in.nextInt();
		monsterStartRow--;
		monsterStartCol--;

		System.out.println("Enter the position of the gold");
		int targetRow = in.nextInt();
		int targetCol = in.nextInt();
		targetRow--;
		targetCol--;
		int matrix[][] = new int[row][col];
		int visited[][] = new int[row][col];
		int count = 0;
		int stepCount = 0;
		for (int i = 0; i < row; i++) {
			for (int j = col; j < col; j++) {
				visited[i][j] = 0;
			}
		}

		int smallesetAdeventurerSteps = miningGold(visited, startRow, startCol, targetRow, targetCol, stepCount, count);
		for (int i = 0; i < row; i++) {
			for (int j = col; j < col; j++) {
				visited[i][j] = 0;
			}
		}
		count = 0;
		stepCount = 0;
		int smallestMonsterSteps = miningGold(visited, monsterStartRow, monsterStartCol, targetRow, targetCol,
				stepCount, count);
		if (smallesetAdeventurerSteps <= smallestMonsterSteps) {
			System.out.println("The Minimum number of steps for the adenturer to reach gold " + smallesetAdeventurerSteps);
			System.out.print("["+startRow+","+startCol+"],");
			for(int k=0;k<smallesetAdeventurerSteps;k++){
				if(startRow<targetRow){
					startRow++;
				}else if(startRow>targetRow){
					startRow--;
				}
				else if(startCol<targetCol){
					startCol++;
				}
				else if(startCol>targetCol){
					startCol++;
				}
				System.out.print("["+startRow+","+startCol+"],");
			}
		
			
		} else
			System.out.println("No Possible Solutions");
	}

	private static int miningGold(int[][] visited, int startRow, int startCol, int targetRow, int targetCol,
			int stepCount, int count) {

		if (count <= row * row) {
			if (startRow == targetRow && startCol == targetCol) {
				steps.add(stepCount);
				count++;
			} else {
				for (int index = 0; index < rowPath.length; index++) {
					int newRow = startRow + rowPath[index];
					int newCol = startCol + colPath[index];
					if (validMove(visited, newRow, newCol)) {
						stepCount++;
						visited[newRow][newCol] = stepCount;
						miningGold(visited, newRow, newCol, targetRow, targetCol, stepCount, count);
						stepCount--;
						visited[newRow][newCol] = 0;
					}
				}
			}
		} else {
			return steps.first();
		}
		return steps.first();
	}

	private static boolean validMove(int visited[][], int rowNew, int colNew) {
		if ((rowNew >= 0) && (rowNew < row) && (colNew >= 0) && (colNew < col) && (visited[rowNew][colNew] == 0)) {
			return true;
		}
		return false;
	}

}
