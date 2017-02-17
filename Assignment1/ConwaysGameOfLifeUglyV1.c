// This assignment is done by pair programming
// Xingneg Wang		|	Jared Manderscheid
// 11144515			|	11159557
// xiw031			|	jam943

#include <stdio.h>

void exit(int);
    int A[100][100]; 
	int B[100][100]; 
int main()
{
FILE *f = fopen("fixedPoint1_100x100.txt", "r");
for (int i = 0; i <=99; i++){
	for (int j = 0; j <= 99; j++){
		A[i][j] = (fgetc(f) == '*');
	}
}

fclose(f);

for (int i = 0; i < 100000; i++) {
	      if (i % 10 == 0)printf("Step %d\n", i);
                        for (int a = 0; a < 100; a++) {
                        for (int b = 0; b < 100; b++) {
				      int c = 0;
				      if (i % 2) {
//					    printf("#10\n");
					      if (a > 0)
					      {
						  if (b > 0)c += B[a - 1] [b - 1];  
					      //if (b > 0)c += B[a * 100 + b - 101];
						  c +=  B[a - 1][b];
					      //c +=  B[a * 100 + b - 100];
						  if (b < 99) c += B[a - 1][b + 1];
						  //if (b < 99) c += B[a * 100 + b - 99];
					      
						  }
					      if (b > 0) c +=  B[a][b-1];
						  //if (b > 0) c +=  B[a * 100 + b - 1];
					      if (b < 99) c += B[a][b+1];
						  //if (b < 99) c += B[a * 100 + b + 1];
					      if (a < 99)
					      {
					      if (b > 0) c +=  B[a + 1] [b-1];
						  //if (b > 0) c +=  B[a * 100 + b + 99];
					      c += B[a + 1] [b];
						  //c += B[a * 100 + b + 100];
					      if (b < 99) c += B[a+1][b+1];
						  //if (b < 99) c += B[a * 100 + b + 101];
					      }

						   A[a][b] = (c == 3) || ((B[a][b] == 1) && c >= 2 && c <= 3); 
					      //A[a * 100 + b] = (c == 3) || ((B[a * 100 + b] == 1) && c >= 2 && c <= 3); 
					      }
				      else {
					      if (a > 0)
					      {
					      if (b > 0) c += A[a-1][b-1];
						  //if (b > 0) c += A[a * 100 + b - 101];
					      c += A[a-1][b];
						  //c += A[a * 100 + b - 100];
					      if (b < 99) c += A[a-1][b+1];
						  //if (b < 99) c += A[a * 100 + b - 99];
					      }
					      if (b > 0) c += A[a][b-1];
						  //if (b > 0) c += A[a * 100 + b - 1];
					      if (b < 99) c += A[a][b+1];
						  //if (b < 99) c += A[a * 100 + b + 1];
					      if (a < 99)
					      {
					      if (b > 0) c += A[a + 1][ b-1];
						  //if (b > 0) c += A[a * 100 + b + 99];
					      c += A[a+1][b];
						  //c += A[a * 100 + b + 100];
					      if (b < 99) c += A[a+1][b+1];
						  //if (b < 99) c += A[a * 100 + b + 101];
					      }
					      //B[a * 100 + b] = (c == 3) || ((A[a * 100 + b] == 1) && c >= 2 && c <= 3); }}}}
						  B[a][b] = (c == 3) || ((A[a][b] == 1) && c >= 2 && c <= 3); }}}}

f = fopen("output.txt", "w");  
for (int i = 0; i <= 99; i++) {
	for (int j = 0; j<= 99; j++){
		fputc(A[i][j] == 1 ? '*' : ' ', f);
	}
	fputc('\n', f);
}
fputc('\n', f);
fclose(f);
}
