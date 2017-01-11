/* xingeng wang
*  11144515
*  xiw031
*/

#include <stdio.h>

void exit(int);
int A[10000]; 
int B[10000];


void printStep(int i)
{
	if (i % 10 == 0)
	{
		printf("Step %d\n", i);
	}
}
		
int updateC(int* array, int a,int b)
{
	int c = 0;
	if (a > 0)
	{
		if (b > 0)
		{
			c += array[a * 100 + b - 101];
		}
		c +=  array[a * 100 + b - 100];
		if (b < 99)
		{
			c += array[a * 100 + b - 99];
		}	
	}
	if (b > 0) 
	{
		c +=  array[a * 100 + b - 1];
	}	
	if (b < 99) 
	{
		c += array[a * 100 + b + 1];
	}
	if (a < 99)
	{
		if (b > 0) 
		{
			c +=  array[a * 100 + b + 99];
		}
		c += B[a * 100 + b + 100];
		if (b < 99) 
		{
			c += array[a * 100 + b + 101];
		}
	}
	return c;
}

int updateArray(int* souce,int a,int b,int c)
{
	return ((c == 3) || ((souce[a * 100 + b] == 1) && c >= 2 && c <= 3));
}

int main()
{
	FILE *f = fopen("block100x100.txt", "r");
	for (int i = 0; i <= 9999; i++)
	{
		if (i > 0 && i % 100 == 0)
		fgetc(f);

		A[i] = (fgetc(f) == '*');
	}

	fclose(f);

	for (int i = 0; i < 100000; i++)
	{
		printStep(i);
        for (int a = 0; a < 100; a++)
		{
            for (int b = 0; b < 100; b++) 
			{
				int c = 0;
				if (i % 2) 
				{
					c = updateC(B,a,b);
					A[a * 100 + b] = updateArray(B,a,b,c);
				}
				else 
				{
					c = updateC(A,a,b);
					B[a * 100 + b] = updateArray(A,a,b,c);
				}
			}
		}
	}


	f = fopen("output.txt", "w");  
	for (int i = 0; i <= 9999; i++) 
	{
		if (i > 0 && i % 100 == 0) 
		{
			fputc('\n', f);
		}
		fputc(A[i] == 1 ? '*' : ' ', f);
	}
	fputc('\n', f);
	fclose(f);
}
