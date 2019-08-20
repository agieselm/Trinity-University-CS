#include <stdio.h>
#include <stdlib.h>

int main(void) {
        int a;   
        int b;   
        int c;
        printf("Enter in three integers: \n");
        if (scanf("%d %d %d", &a, &b, &c) == 3)
        {
                printf("%d %d %d \n", a, b, c);
        } 
        else 
        {
                printf("That is not an integer \n");
        }

        exit(0);
}
