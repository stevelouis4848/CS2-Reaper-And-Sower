/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs2.reapandsow.ass.pkg1;
/**
 *
 * @author Ideapad
 */
import java.util.Scanner;
public class CS2ReapAndSowAss1 {

    /**
     * @param args the command line arguments
     */
    
    public static int n;
    public static int m;
    public static int input[][];
        
    public static void main(String[] args) {
        
        // TODO code application logic here
        //TO read the inouts into the program.
        
        System.out.print("Please enter inputs");
        Scanner sc = new Scanner(System.in);
       
        n = sc.nextInt();
        m = sc.nextInt();
        
        input = new int[n][m];
        
        int pastSolutionBinaryRow[] = new int[n];
        int PastSolutionBinaryCollum[] = new int[m];
                    
        for(int i = 0; i < n; i++){           
            for(int j = 0; j < m; j++){
                
                input[i][j] = sc.nextInt();
            }
        }
        
        solveFunction(0, 0, 0);          
    }
              
    public static boolean solveFunction(int row, int collum, int numOfSol){

       // base cases
        printOutput(n, m, input, numOfSol);
        
         if(collum == m){
           
            row = row + 1;
            collum = 0;
        }
         
        if(row > n - 1){
            
            numOfSol++;
            printOutput(n, m, input, numOfSol);
            return true;
        }
            
         if(input[row][collum] != -1 ){
            
            return solveFunction(row, collum + 1, numOfSol);
        }
               
        //recursion and backtracking
        
        for(int i = 1; i >= 0; i--){
            
            input[row][collum] = i;
            
            if(isValid(input, row, collum)){
                
                if(solveFunction(row, collum + 1, numOfSol)){
                    return true;
                }
            }         
        }        
        input[row][collum] = -1; 
       return false;
    }
    
    public static boolean isValid(int input[][], int row, int collum){
        
        if(!checkCollumConti(input, row, collum) || !checkRowConti(input, row, collum) 
                             || !checkRowMax(input, row, collum, m/2) || !checkCollumMax(input, row, collum, n/2)){
            
            return false;
        }
        
        return true;
    }
        
    public static boolean checkRowConti(int input[][], int currentRow, int currentCollum){
       
        int inUsedRow = 0;
       
        for(int i = 0; i < currentCollum; i++){
         
            if(input[currentRow][i] == 1){
             
                inUsedRow++;             
            }
            else{
                inUsedRow = 0;
            }
        }
     
        if(inUsedRow >= 3){
                       
           // System.out.printf("3 rowConti in row %d: row %d, collum %d \n", currentRow+1, currentRow+1, currentCollum+1);
            return false;
        }
        return true;     
   }
    
    public static boolean checkCollumConti(int input[][],int currentRow, int currentCollum){
       
        int inUsedCollum = 0;
   
        for(int i = 0; i < currentRow; i++){

            if(input[i][currentCollum] == 1){

                inUsedCollum++;             
            }
            else{
                inUsedCollum = 0;
            }               
        }

        if(inUsedCollum >= 3){
            
             //System.out.printf("3 collumConti in collum %d: row %d, collum %d \n", currentCollum, currentRow, currentCollum);
            
            return false;
        }
        return true;    
   }
    
    public static boolean checkRowMax(int input[][], int currentRow, int currentCollum, int rowMax){
         
        int inUsedRow = 0;
       
        for(int i = 0; i < m; i++){
         
            if(input[currentRow][i] == 1){
             
                inUsedRow++;             
            }
        }
         
        //System.out.printf("In used Rowmax %d\n",inUsedRow);
        if(inUsedRow > rowMax){

            //System.out.printf("Max in row %d: row %d, collum %d\n", currentRow+1, currentRow+1, currentCollum+1);
            
            return false;
        }

        else if((currentCollum == m - 1) && (inUsedRow < rowMax) ){
            
           // System.out.printf("Max in row not met %d: row %d, collum %d\n", currentRow+1, currentRow+1, currentCollum+1);
            return false;
        }
   
        return true;
    }
     
    public static boolean checkCollumMax(int input[][],int currentRow, int currentCollum, int collumMax){
      
        int inUsedCollum = 0;
       
        for(int i = 0; i < n; i++){

            if(input[i][currentCollum] == 1){

                inUsedCollum++;             
            }
        }
        
       // System.out.printf("In used collumMax %d\n", inUsedCollum);
        
        if(inUsedCollum > collumMax){

           // System.out.printf("Max in collum %d: row %d, collum %d\n", currentCollum+1, currentRow+1, currentCollum+1);

            return false;
        }

        else if((currentRow == n - 1) && (inUsedCollum < collumMax) ){
            
           // System.out.printf("Max in collum not met %d: row %d, collum %d\n", currentCollum+1, currentRow+1, currentCollum+1);
        
        }

        return true;
    }
    
    public static boolean checkRowPattern(int input[][], int currentRow){
    
        return true;
    }
    
    public static boolean checkCollumPattern(int input[][],int currentCollum){
    
     return true;
    }
    public static void printOutput(int n, int m, int output[][], int numOfSol){
        
       int squaresWorked = 0;
       
       System.out.print("\n");
       
        for(int i = 0; i < n; i++){
        
            for(int j = 0; j < m; j++){
                
                System.out.printf("%d ", input[i][j]);
                
                if(input[i][j] == 1){
                    
                    squaresWorked++;
                }
            
            }
            System.out.print("\n");
        }
        
        System.out.printf("Total squares worked %d / %d\n", squaresWorked, n*m);
        System.out.printf("number of solutions: %d\n", numOfSol);
            
    }
}
