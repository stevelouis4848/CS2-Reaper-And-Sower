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
import java.lang.Math;
public class CS2ReapAndSowAss1 {

    /**
     * @param args the command line arguments
     */
    
    public static int n;
    public static int m;
    public static int input[][];
    public static int pastSolutionBinaryRow[];
    public static int pastSolutionBinaryCollum[];
    
        
    public static void main(String[] args) {
        
        // TODO code application logic here
        //TO read the inouts into the program.
        
        System.out.print("Please enter inputs");
        Scanner sc = new Scanner(System.in);
       
        n = sc.nextInt();
        m = sc.nextInt();
        
        input = new int[n][m];
        
        pastSolutionBinaryRow = new int[n];
        pastSolutionBinaryCollum = new int[m];
                    
        for(int i = 0; i < n; i++){           
            for(int j = 0; j < m; j++){
                
                input[i][j] = sc.nextInt();
            }
        }
        
        solveFunction(0, 0, 0);          
    }
              
    public static boolean solveFunction(int row, int collum){

       // base cases
        //printOutput(n, m, input, pastSolutionBinaryRow, pastSolutionBinaryCollum);
        
         if(collum == m){
           
            row = row + 1;
            collum = 0;
        }
         
        if(row > n - 1){
            
            printOutput(n, m, input, pastSolutionBinaryRow, pastSolutionBinaryCollum);
            return true;
        }
            
         if(input[row][collum] != -1 ){
            
            return solveFunction(row, collum + 1);
        }
               
        //recursion and backtracking
        
        for(int i = 1; i >= 0; i--){
            
            input[row][collum] = i;
            
            if(isValid(input, pastSolutionBinaryRow, pastSolutionBinaryCollum, row, collum)){
                
                if(solveFunction(row, collum + 1)){
                    return true;
                }
            }         
        }        
        input[row][collum] = -1; 
       return false;
    }
    
    public static boolean isValid(int input[][],int pastSolutionBinaryRow[], 
            int  PastSolutionBinaryCollum[], int row, int collum){
        
        if(!checkCollumConti(input, row, collum) || !checkRowConti(input, row, collum) 
                             || !checkRowMax(input, row, collum, m/2) || !checkCollumMax(input, row, collum, n/2)
                             || !checkCollumPastSolutions( PastSolutionBinaryCollum,input,row ,collum)
                             || !checkRowPastSolutions(pastSolutionBinaryRow,input,row ,collum)){
            
            return false;
        }
        
        return true;
    }
        
    public static boolean checkRowConti(int input[][], int currentRow, int currentCollum){
       
        int inUsedRow = 0;
       
        if(currentCollum > 1){
           
            inUsedRow = input[currentRow][currentCollum];
            
            if(input[currentRow][currentCollum - 1] == inUsedRow && 
                    input[currentRow][currentCollum - 2] == inUsedRow){
                
                return false;
            }
        }
    
        return true;     
   }
    
    public static boolean checkCollumConti(int input[][],int currentRow, int currentCollum){
       
        int inUsedCollum = 0;
   
        if(currentRow > 1){
           
            inUsedCollum = input[currentRow][currentCollum];
            
            if(input[currentRow - 1][currentCollum ] == inUsedCollum && 
                    input[currentRow - 2][currentCollum] == inUsedCollum){
                
                return false;
            }
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
    
    public static boolean checkRowPastSolutions(int pastSolutionBinaryRow[], int input[][],int currentRow,int currentCollum){
    
        int currentRowBinary = 0;
        
        if(currentCollum == m - 1 ){
            
            for(int i = 0; i < m; i++){
                
                 currentRowBinary += input[currentRow][i] * Math.pow(2, i); 
            }
            System.out.printf("Row: %d after math Binary: %d\n", currentCollum, pastSolutionBinaryRow[currentCollum]);
            
            for(int i = 0; i < currentRow; i++ ){
                
                if(pastSolutionBinaryRow[i] == currentRowBinary){
                    return false;
                }              
            }
            
             pastSolutionBinaryRow[currentRow] = currentRowBinary;  
             System.out.printf("Row: %d Binary : %d\n", currentRow, pastSolutionBinaryRow[currentRow]);
        }
        return true;
    }
    
    public static boolean checkCollumPastSolutions(int pastSolutionBinaryCollum[], int input[][],int currentRow, int currentCollum){
    
        int currentCollumBinary = 0;
        
         if(currentRow == n - 1){
            
            for(int i = 0; i < n; i++){
                
                 currentCollumBinary += input[i][currentCollum] * Math.pow(2, i);
                 
            }
            System.out.printf("Collum: %d After math Binary : %d\n", currentCollum, pastSolutionBinaryCollum[currentCollum]);
            for(int i = 0; i < currentCollum; i++ ){
                
                if(pastSolutionBinaryCollum[i] == currentCollumBinary){
                    return false;
                }              
            }         
             pastSolutionBinaryCollum[currentCollum] = currentCollumBinary; 
             System.out.printf("Collum: %d Binary : %d\n", currentCollum, pastSolutionBinaryCollum[currentCollum]);
        }
            
       
     return true;
    }
    public static void printOutput(int n, int m, int output[][], 
            int  pastSolutionBinaryRow[], int pastSolutionBinaryCollum[] ){
        
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
        
        System.out.print("Row Binary Solutions:\n");
        for(int i = 0; i < n ; i++){
            
            System.out.printf("%d ", pastSolutionBinaryRow[i]);
        }
        
        System.out.print("\n Collum Binary Solutions:\n");
        for(int i = 0; i < m ; i++){
            
            System.out.printf("%d ", pastSolutionBinaryCollum[i]);
        }
        
        System.out.printf("\n Total squares worked %d / %d\n", squaresWorked, n*m);      
    }
}
