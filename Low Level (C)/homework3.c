#include <stdio.h>
#include <stdlib.h>

int gcd(int n1, int n2) {

	if(n2 == 0) {
		return n1;
	} else {
		gcd(n2,n1%n2);
	}
}

int main(void) {
	
	int a,b;
	int n1, n2;
	printf("Input a non-negative integer \n");
	int check; 
	check = scanf("%d %d", &a, &b);

	if(check < 2) {
		printf("Error\n");
	exit(1);
	}
	else if(a==0 && b==0) {
		printf("Error\n");
	exit(1);
	}
	else if(a<0 || b<0) {
		printf("Error\n");
	exit(1);
	}	
	else if(a>=b) {
	n1 = a;
	n2 = b;
	}
	else {
	n2 = a; 
	n1 = b;
	} 

printf("THE GREATEST COMMON DIVISOR IS: %d", gcd(n1, n2));

return 0;
} 
