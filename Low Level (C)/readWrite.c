/* Copies a file.
 *    Specify the source file and the destination file on the command line.
 *    */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	FILE *source_fp, *dest_fp;
	char ch;

	if (argc != 3)
	{
		fprintf(stderr, "Usage: COPY source dest \n");
		exit(1);
	}
	
	if ((source_fp = fopen(argv[1], "r")) == NULL)
	{
		fprintf(stderr, "Can't open %s. \n", argv[1]);
		exit(1);
	}

        if ((dest_fp = fopen(argv[2], "w")) == NULL)
        {
                fprintf(stderr, "Can't open %s. \n", argv[2]);
		fclose(source_fp);
                exit(1);
        }

	while ((ch = fgetc(source_fp)) != EOF)
		fputc(ch, dest_fp);

	fclose(source_fp);
	fclose(dest_fp);

	return 0;
}
