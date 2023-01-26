
/**
 * This class does actions on a given magic square,
 * and finally checks if a given matrix is a magic square using all 4 first methods.
 */
public class MagicSquare
{
    /**
     * This method returns the sum of a given row in the magic square
     * @param mat the magic square
     * @param row the row in the magic square to sum
     * @return sum of the number in specific row
     */
    public static int sumRow (int[][] mat, int row){
        int sum=0;
        for (int i=0 ; i<mat[0].length ; i++){
            sum += mat[row][i];
        }
        return sum;
    }
    
    /**
     * This method returns the sum of a given column in the magic square
     * @param mat the magic square
     * @param col the column in the magic square to sum
     * @return sum of the number in specific column
     */
    public static int sumCol (int [][] mat, int col){
        int sum=0;
        for (int i=0 ; i<mat.length ; i++){
            sum += mat[i][col];
        }
        return sum;
    }
    
    /**
     * This method returns the sum of the primary diagonal in the magic square
     * @param mat the magic square
     * @return the sum of the primary diagonal in the magic square
     */
    public static int sumPrimaryDiag (int[][] mat){
        int sum=0;
        for (int i=0 ; i<mat.length && i<mat[0].length ; i++){
            sum += mat [i][i];
        }
        return sum;
    }
    
    /**
     * This method returns the sum of the secondary diagonal in the magic square
     * @param mat the magic square
     * @return the sum of the secondary diagonal in the magic square
     */
    public static int sumSecondaryDiag (int [][] mat){
        int sum=0;
        for (int i=0, j=mat[0].length-1 ; i<mat.length && j>=0 ; i++, j--){
            sum += mat [i][j];
        }
        return sum;
    }
    
    /**
     * This method checks if a matrix is a square matrix
     * @param mat the matrix
     * @return true if the matrix is a square matrix
     */
    public static boolean isMagicSquare (int [][] mat){   
        if (mat.length != mat[0].length)
            return false;
        
        //checking range for numbers 1 - n^2
        for (int i=0; i<mat.length ; i++){
            for (int j=0 ; j<mat[0].length ; j++){
                if ( mat[i][j] < 1 || mat [i][j] > (mat.length*mat[0].length))
                    return false;
            }
        }    
        
        //cheking that all numbers in the array are differrent by creating new array
        int [] array= new int [mat.length*mat[0].length];// creating a one dimentional array with all the numbers from the magic square
        for (int i=0 ; i<mat.length ; i++){
            for (int j=0 ; j<mat[0].length ; j++){
                array[i*mat[0].length + j] = mat[i][j];
            }
        }
        //cheking that all numbers in the array are differrent
        for (int i=0 ; i<array.length ; i++){
            for (int j= i+1 ; j< array.length ; j++){
                if (array [i] == array [j])
                    return false;
            }
        }
        
        int comparisonVariable = sumPrimaryDiag(mat); //if magic sqaure- comparisonVariable equals to the sum of rows, columns and diagonals.     
        
        if (sumSecondaryDiag (mat) != comparisonVariable)// checking equal diagonals
            return false;
        
        for (int i=0 ; i< mat.length ; i++){// cheking equals to all rows
            if (sumRow (mat,i) != comparisonVariable)
                return false;
        }
        
        for (int i=0 ; i<mat[0].length ; i++){// cheking equals to all columns
            if (sumCol (mat,i) != comparisonVariable)
                return false;
        }
        
        return true;
    }
}
