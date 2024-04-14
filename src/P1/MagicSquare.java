package P1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;

public class MagicSquare {
    public static boolean isLegalMagicSquare(String fileName) {
        int row=-1; //定义行数
        File myfile = new File(fileName);
        try {
            //得到行数
            BufferedReader yes = new BufferedReader(new FileReader(myfile));
            String mytemp1 = null;
            do {
                row++; //得到行数
            } while((mytemp1=yes.readLine()) != null);
            yes.close();

            //defining first type error: number of rows and columns are not equal, not matrix
            @SuppressWarnings("resource")
            BufferedReader faultone = new BufferedReader(new FileReader(myfile));
            String mytemp2 = null;
            String[] allrows;
            int col;
            while((mytemp2=faultone.readLine()) != null) {
                allrows = mytemp2.split("\t");   //write each row values into an array
                col = allrows.length;
                if(col!=row) {
                    System.out.println(fileName + ": Cannot create a matrix because the number of rows and columns are not equal");
                    return false;
                }
            }
            allrows = null;
            faultone.close();

            //defining second type error: any value containing non-positive integer
            @SuppressWarnings("resource")
            BufferedReader faulttwo = new BufferedReader(new FileReader(myfile));
            String mytemp3 = null;
            int i = 0;
            int j;
            while((mytemp3=faulttwo.readLine()) != null) {
                allrows = mytemp3.split("\t");
                col = allrows.length;
                for(j=0; j<col; j++) {
                    if(allrows[j].contains(".") || allrows[j].contains("-")) {
                        System.out.println(fileName + ": Contains a non-positive integer");
                        return false;
                    }
                }
                i++;
            }
            allrows=null;
            faulttwo.close();

            //defining third type error: if delimiter of each value is not tab, throw exception
            int[][] matrix = new int[row][row];
            i=0;
            try {
                BufferedReader faultthree = new BufferedReader(new FileReader(myfile));
                mytemp3 = null;
                while((mytemp3=faultthree.readLine()) != null) {
                    allrows = mytemp3.split("\t");
                    col = allrows.length;
                    for(j=0; j<col; j++) {
                        matrix[i][j] = Integer.valueOf(allrows[j]);
                    }
                    i++;
                }
                faultthree.close();
            } catch(Exception e) /*Integer.valueOf(), this method cannot handle space as delimiter*/ {
                System.out.println(fileName + ": Tab was not the delimiter");
                e.printStackTrace();
                return false;
            }

            col = row;
            int[] totalrow = new int[row];
            int[] totalcol = new int[col];
            int[] diag = new int[2];
            int temp1,temp2,temp3;
            for(i=0; i<row; i++) {
                totalrow[i] = 0;
            }
            for(j=0; j<col; j++) {
                totalcol[j] = 0;
            }
            diag[0] = 0;
            diag[1]=0;

            //计算行，列，对角线的和并判断是否为MagicSquare
            //计算每一行的和保存在数组sumhang
            for(i=0; i<row; i++) {
                for(j=0; j<col; j++) {
                    totalrow[i] = totalrow[i]+matrix[i][j];
                }
            }
            //计算某一列的和保存在sumlie
            for(i=0; i<col; i++) {
                for(j=0; j<row; j++) {
                    totalcol[i] = totalcol[i]+matrix[j][i];
                }
            }
            //计算对角线的和保存在sumdia
            for(j=0; j<row; j++) {
                diag[0] = diag[0]+matrix[j][j];
                diag[1] = diag[1]+matrix[j][row-j-1];
            }
            //checking whether the matrix is a Magic Square
            temp1 = totalrow[0];temp2=totalcol[0];temp3=diag[0];
            if(temp1!=temp2 || temp1!=temp3 || temp2!=temp3) {
                return false;
            }
            else {
                for(i=1; i<row; i++) {
                    if(totalrow[i] != temp1) {
                        return false;
                    }
                }
                for(i=1; i<col; i++) {
                    if(totalcol[i] != temp2) {
                        return false;
                    }
                }
                if(diag[1] != diag[0]) {
                    return false;
                }
            }
        }catch(Exception e) /*defining third type error：throw exception when the delimiter is not tab*/ {
            System.out.println(fileName + " Tab was not the delimiter");
            e.printStackTrace();
            return false;
        }

        System.out.println("Successfully read " + fileName);
        return true;
    }

    @SuppressWarnings("resource")
    public static boolean generateMagicSquare(int n) {
        if (n % 2 == 0 || n <= 0) {   //判断输入的n是否为负数，若是则返回false
            return false;
        }
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {  //构造奇数阶MagicSquare
            magic[row][col] = i;
            if (i % n == 0)    //斜着填
                row++;
            else {
                if (row == 0)    //上边满了往下构造
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))   //右边满了往左构造
                    col = 0;
                else
                    col++;
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");   //输出MagicSquare
            System.out.println();
        }
        FileWriter writefile = null;
        BufferedWriter mywriter = null;
        try {
            writefile = new FileWriter("src/P1/txt/6.txt");
            mywriter = new BufferedWriter(writefile);
        } catch (Exception e) {
            System.out.println("Writing to Error 1");
            e.printStackTrace();
            return false;
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                try {
                    mywriter.write(magic[i][j] + "\t");
                } catch (Exception e) {
                    System.out.println("Writing to Error 2");
                    e.printStackTrace();
                    return false;
                }
            }
            try {
                mywriter.write("\n");
            } catch (Exception e) {
                System.out.println("Writing to Error 3");
                e.printStackTrace();
                return false;
            }
        }
        try {
            if(mywriter!=null)
                mywriter.close();
        } catch (Exception e) {
            System.out.println("Writing to Error 4");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Program is checking the text files to see whether any of it is a Magic Square...");
        System.out.println("File 1: " + MagicSquare.isLegalMagicSquare("src/P1/txt/1.txt") + "\n");
        System.out.println("File 2: " + MagicSquare.isLegalMagicSquare("src/P1/txt/2.txt") + "\n");
        System.out.println("File 3: " + MagicSquare.isLegalMagicSquare("src/P1/txt/3.txt") + "\n");
        System.out.println("File 4: " + MagicSquare.isLegalMagicSquare("src/P1/txt/4.txt") + "\n");
        System.out.println("File 5: " + MagicSquare.isLegalMagicSquare("src/P1/txt/5.txt") + "\n");

        int a;
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter a dimension for Magic Square：");
            a = in.nextInt();
            if (a%2 == 0) {
                System.out.println("Cannot create a Magic Square with an even number, return value: " + a + MagicSquare.generateMagicSquare(a) + "\n");
            } else if (a < 0) {
                System.out.println("Cannot create a Magic Square with a negative number, return value: " + a + MagicSquare.generateMagicSquare(a) + "\n");
            } else {
                System.out.println("Created a Magic Square with a size of " + a + "，and was stored in 6.txt");
                MagicSquare.generateMagicSquare(a);
                System.out.println("Checking whether the file is a Magic Square...");
                System.out.println(MagicSquare.isLegalMagicSquare("src/P1/txt/6.txt") + "\n");
            }
        }
    }
}