/* stdin->TEST and TEST->stdout */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 10

int main(void)
{

	char *str, *str1;
	FILE *fp;

	str = malloc(N);
	str1 = malloc(N);
	if (!str || !str1)
	{
		printf("Out of memory! \n");
		exit(1);
	}

	if ((fp = fopen("TEST", "w+")) == NULL)
	{
		printf("Can't open file. \n");
		exit(1);
	}

	printf("Enter a string: \n");
	fgets(str, N, stdin); // the same as gets(str)
	fputs(str, fp);

	//now, read and display the file
	//	rewind(fp); // reset file position indicator to start of the file
	//		fgets(str1, N, fp);
	//			fprintf(stdout, str1); // the same as printf(str);
	//				printf("\n");
	//
	//					fclose(fp);
	//
	//						return 0;
	//						}
