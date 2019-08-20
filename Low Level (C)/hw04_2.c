#include <stdio.h>
#include <stdlib.h> /* has rand(), srand(), RAND_MAX */

int main(void) {

	int seed;

	printf("seed?\n");
	if (scanf("%d", &seed) != 1) {
		printf("invalid input\n");
		return 1;
	}
	if (seed <= 0) {
		printf("invalid input\n");
		return 1;
	}

	srand(seed);

	int sample;
	
	printf("How many samples?\n");
	if(scanf("%d", &sample) != 1) {
		printf("invalid input\n");
		return 1;
	}

	int bins;	
	
	printf("How many bins?\n");
	if(scanf("%d", &bins) != 1) {
		printf("invalid input\n");
		return 1;
	}
	

	int bin_arr[bins];

	for(int x = 0; x < bins; ++x) {
        bin_arr[x] = 0;
	
	}           


	for(int x = 0; x < sample; ++x) {
	int n = rand();
	int index = n % bins;
	bin_arr[index]++;
		
	}
	
	printf("Counts using remainder method:\n");

	for(int x = 0; x < bins; ++x) {
	printf("%d %d \n", x, bin_arr[x]);

	}
	
	int bin2_arr[bins];

	for(int x = 0; x < bins; ++x) {
	bin2_arr[x] = 0;

	}

	for(int x = 0; x < sample; ++x) {
	int n = rand();
	int index = ((int) ((((double) bins) * n)/(RAND_MAX+1.0)));
	bin2_arr[index]++;

	}
	
	printf("Counts using quotient method:\n");

	for(int x = 0; x < bins; ++x) {
	printf("%d %d \n", x, bin2_arr[x]);

	}

	return 0;

}
