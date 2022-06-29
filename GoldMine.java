import java.util.TreeSet;

public class GoldMine {
	static TreeSet<Integer> steps= new TreeSet<Integer>(); 
	static int row;
	static int col;

	static int [] rowPath = {0,0,-1,1};
	static int [] colPath = {1 ,-1,0 ,0};
	
		

	public static int miningGold( int[][] visited, int startRow, int startCol, int targetRow, int targetCol,
			int stepCount,int count) {
		
		if (count <= row*row) {
			if (startRow==targetRow && startCol== targetCol) {
				steps.add(stepCount);
				count++;
			}
			else {
				for (int index=0 ;index< rowPath.length ; index++ ) {
					int newRow= startRow+ rowPath[index];
					int newCol = startCol+colPath[index];
					if (validMove(visited, newRow,newCol)) {
						 stepCount++;
	                     visited[newRow][newCol] = stepCount;
	                     miningGold(visited, newRow, newCol, targetRow,targetCol,stepCount,count);
						 stepCount--;
	                     visited[newRow][newCol] = 0;
					}
				}
			}
		}
		else {
			return steps.first();
		}
		return steps.first();
	}

	private static boolean validMove(int visited[][], int rowNew, int colNew)
	{
	    if ((rowNew >= 0) && (rowNew < row) && (colNew >= 0) && (colNew < col) && (visited[rowNew][colNew] == 0))
	    {
	        return true;
	    }
	    return false;
	}

	}


