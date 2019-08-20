#include <stdio.h>

int main(void) {
	int sz = 6;
	double a[sz];
	for (int i = 0; i < sz; ++i) {
		a[i] = (i+1) *0.1;
	}

	for (int i = 0; i < sz; ++i) {
		printf("%f\n", a[i]);
	}

	printf("index? (up to %d)\n", sz);
	int index;
	if (scanf("%d", &index)) {
		a[index] = 0;
		for (int i = 0; i < sz; ++i) {
			printf("%f\n", a[i]);
		}
	}
	else {
		printf("error\n");
	}
	return 0;
}
