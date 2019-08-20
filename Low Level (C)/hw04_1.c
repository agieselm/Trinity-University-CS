#include<stdio.h>
#include "math.h"

int main(void) {

int x, maximum, times = 0;
double thresh, guess, gsqr;

printf("Enter values for input, threshold, maximum iterations\n");
	
	if(scanf("%d, %lf, %d", &x, &thresh, &maximum) != 3) {
		printf("invalid input\n");
		return 1;
	}
	
	if(thresh < 0) {
		printf("invalid input\n");
	}
	if(maximum < 0) {
		printf("invalid input\n");
	}
printf("You entered: input %d, threshold %d, maximum iterations %d number\n", x, thresh, maximum);

	guess = x;
	gsqr = (guess * guess) - x;

	while( gsqr > thresh && times < maximum) {
		guess = ((guess + (x/guess))/2);
		gsqr = (guess * guess) - x;
		times += 1;
	
	}
	
printf("square root of %d\n", x);
printf("With Newton's method: (threshold %lf): %lf (%d iterations)\n", thresh, guess, times);	

double library = sqrt(x);

printf("With library function: %lf\n", library);

double difference = guess - library;

printf("Difference:%lf", difference);

}
