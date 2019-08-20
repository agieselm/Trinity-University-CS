#include <stdio.h>

int main(void) {
	int sum = 0;
	int in;
	printf("enter ints, anything else to stop\n");
	while (scanf("%d", &in) == 1) {
		sum += in;
	}
	printf("%d\n", sum);
	return 0;

}
