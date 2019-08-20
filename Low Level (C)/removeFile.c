/*Double check before erasing a file.
 *   Specify the file on the command line.
 *   */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char *argv[])
{
	char ch;

	if (argc != 2)
	{
		printf("Usage: ERASE filename \n");
		exit(1);
	}

        fprintf(stdout, "Erase %s? (Y/N): ", argv[1]);
	ch = fgetc(stdin); // the same as ch = getchar();
	if (toupper(ch) == 'Y'){
		if (remove(argv[1])){
			fprintf(stdout, "Can't erase file %s. \n", argv[1]);
			exit(1);
		}	
		else fprintf(stdout, "Done.\n");
	}

	return 0;
}
