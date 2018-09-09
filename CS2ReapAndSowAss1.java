// Steve Louis
// CS2 Travis Meade 
// Reap And Sow Assignment 1
// 09/08/2018

import java.util.Scanner;
import java.lang.Math;
public class CS2ReapAndSowAss1 {

    public static int n;
    public static int m;
    public static int input[][];
    public static int pastSolutionsBinaryRow[];
    public static int pastSolutionsBinaryCollum[];
           
    public static void main(String[] args) {
               
        // TO read the inputs into the program.
        Scanner sc = new Scanner(System.in);
       
        n = sc.nextInt();
        m = sc.nextInt();
        
        input = new int[n][m];
        
        pastSolutionsBinaryRow = new int[n];
        pastSolutionsBinaryCollum = new int[m];
                  
        //  Reads in the preset values for the grid
        for(int i = 0; i < n; i++){           
            for(int j = 0; j < m; j++){
                
                input[i][j] = sc.nextInt();
            }
        }
          
        // In case the grid is unsolvalble.
        if(!solveFunction(0, 0)){
            System.out.print("impossible\n");
        }       
    }
              
    public static boolean solveFunction(int row, int collum){

       // When the end of a row is reach values are set to go to next row            
        if(collum == m){
            row = row + 1;
            collum = 0;
        }
          
        //  A solution is found if we past the last row
        if(row > n - 1){
            
            printOutput(n, m, input, pastSolutionsBinaryRow, pastSolutionsBinaryCollum);
            return true;
        }
          
        /*  Checks if the current square passes the tests if value already preset
            if it passes we move to the next square.Otherwise we return false to
            backtrack to a previous solutionn
        */
        if(input[row][collum] != -1  ){
             
            if(!isValid(input, pastSolutionsBinaryRow, pastSolutionsBinaryCollum, row, collum))
                     return false;
                 
            if(!isValid(input, pastSolutionsBinaryRow, pastSolutionsBinaryCollum, row, collum))
                    return false;
                                       
           return solveFunction(row, collum + 1);
        }
               
        /* Inserts and checks the validity of the values 1 and 0 in the current 
           position in the grid */
        
        for(int i = 1; i >= 0; i--){
            
            input[row][collum] = i;
            
           // printOutput(n, m, input, pastSolutionsBinaryRow, pastSolutionsBinaryCollum);
            
            if(isValid(input, pastSolutionsBinaryRow, pastSolutionsBinaryCollum, row, collum)){
                
                if(solveFunction(row, collum + 1)){
                    return true;
                }
            }         
        }   
        
        /* when neither 1 or 0 worked, the value of the current position is reset
           to -1 and we return false in order to backtrack to a previous move. */
        input[row][collum] = -1; 
        
        return false;
    }
    
    /*
        All the tests are called and evaluate in this function,if any test fails 
        then this function returns false.
    */
    public static boolean isValid(int input[][],int pastSolutionsBinaryRow[], 
            int  pastSolutionsBinaryCollum[], int row, int collum){
        
        if(!checkCollumConti(input, row, collum) || !checkRowConti(input, row, collum) 
                             || !checkRowMax(input, row, collum, m/2) || !checkCollumMax(input, row, collum, n/2)
                             || !checkCollumPastSolutions(pastSolutionsBinaryCollum,input,row ,collum)
                             || !checkRowPastSolutions(pastSolutionsBinaryRow,input,row ,collum)){         
            return false;
        }            
        return true;
    }
        
    // Checks if a row has 3 or more squares equal to 1 in a row.        
    public static boolean checkRowConti(int input[][], int currentRow, int currentCollum){
       
        int inUsedRow;
       
        // No need to check more then three in a row before index 2 of that row.
        if(currentCollum > 1){
           
            inUsedRow = input[currentRow][currentCollum];
            
            if(input[currentRow][currentCollum - 1] == inUsedRow && 
                    input[currentRow][currentCollum - 2] == inUsedRow){
                
                return false;
            }
        }
    
        return true;     
   }
    
    // Checks if a collum has 3 or more squares equal to 1 in a row.        
    public static boolean checkCollumConti(int input[][],int currentRow, int currentCollum){
       
        int inUsedCollum;
        
        // No need to check more then three in a collum before index 2 of that collum
        if(currentRow > 1){
           
            inUsedCollum = input[currentRow][currentCollum];
            
            if(input[currentRow - 1][currentCollum ] == inUsedCollum && 
                    input[currentRow - 2][currentCollum] == inUsedCollum){
                
                return false;
            }
        }
        return true;    
   }
    
     /*
        Checks if a row has passed the max ammount of activated squares equal
        to 1.
    */
    public static boolean checkRowMax(int input[][], int currentRow, int currentCollum, int rowMax){
         
        int inUsedRow = 0;
       
        // Adds up number of square in the current row  with value of 1.
        for(int i = 0; i < m; i++){
         
            if(input[currentRow][i] == 1){
                inUsedRow++;             
            }
        }
        
        // For cases where the row is at the end and contains too many values of 1.
        if(inUsedRow > rowMax){
            return false;
        }
        // For cases where the row is at then and does not contain enough values of 1.
        else if((currentCollum == m - 1) && (inUsedRow < rowMax) ){
            return false;
        }       
        return true;
    }
     
     /*
        Checks if a collum has passed the max ammount of activated squares equal
        to 1.
    */
    public static boolean checkCollumMax(int input[][],int currentRow, int currentCollum, int collumMax){
      
        int inUsedCollum = 0;
        
        // Adds up number of square in the current collum  with value of 1.
        for(int i = 0; i < n; i++){

            if(input[i][currentCollum] == 1){
               inUsedCollum++;             
            }
        }
        
        // For cases where the collum is at the end and contains too many values of 1.
        if(inUsedCollum > collumMax){
            return false;
        }
        // For cases where the collum is at then and does not contain enough values of 1.
        else if((currentRow == n - 1) && (inUsedCollum < collumMax)){
            return false;
        }      
        return true;
    }
    
    /*
        At the End of solved row the state of the row is stored as unique binary 
        number to compare against furure and past row solutions to avoid duplcate row states.
    */
    public static boolean checkRowPastSolutions(int pastSolutionsBinaryRow[], int input[][],int currentRow,int currentCollum){
    
        int currentRowBinary = 0;
        
        // Solutions for rows are only tore and compared at the end of the rows.
        if(currentCollum == m - 1 ){
            
            // The collum number is treated as the exponennt,and the value 1 0r 0 gives it the binary property.
            for(int i = 0; i < m; i++){
                
                currentRowBinary += input[currentRow][i] * Math.pow(2, i); 
            }
         
            for(int i = 0; i < currentRow; i++ ){
                
                if(pastSolutionsBinaryRow[i] == currentRowBinary){
                    return false;
                }              
            }
            
            pastSolutionsBinaryRow[currentRow] = currentRowBinary;          
        }
        return true;
    }
    
    /*
        At the End of solved collum the state of the collum is stored as unique binary 
        number to compare against furure and past collum solutions to avoid duplcate row states.
    */
    public static boolean checkCollumPastSolutions(int pastSolutionsBinaryCollum[], int input[][],int currentRow, int currentCollum){
    
        int currentCollumBinary = 0;
         
        // Solutions for collums are only tore and compared at the end of the collums.
         if(currentRow == n - 1){
            
            // The row number is treated as the exponennt,and the value 1 0r 0 gives it the binary property.
            for(int i = 0; i < n; i++){
                currentCollumBinary += input[i][currentCollum] * Math.pow(2, i);                
            }
         
            for(int i = 0; i < currentCollum; i++ ){
                
                if(pastSolutionsBinaryCollum[i] == currentCollumBinary){
                    return false;
                }              
            } 
            
            pastSolutionsBinaryCollum[currentCollum] = currentCollumBinary;  
        }
     return true;
    }
    
    // Prints thse current state of the grid to the screen.
    public static void printOutput(int n, int m, int output[][], 
            int  pastSolutionsBinaryRow[], int pastSolutionsBinaryCollum[] ){
            
       System.out.print("\n");
       
        // Goes to the ith row and jth collum and prints the value there.
        for(int i = 0; i < n; i++){
            
            for(int j = 0; j < m; j++){
                
                System.out.printf("%d ", input[i][j]);
            }
            System.out.print("\n");
        }
    }
}
