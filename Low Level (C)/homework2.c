#include <stdio.h>
#include <stdlib.h>

int main(void) {
  int seconds; 
  printf("input integer \n");
  scanf("%d", &seconds);
  printf("%d \n", seconds);
  int pSeconds = seconds;
  int yearC = 0;
  int yearN = 31622400;
  int dayC = 0;
  int dayN = 86400;
  int hourC = 0;
  int hourN = 3600;
  int minuteC = 0;
  int minuteN = 60;

  int divide(int n, int c){

	if(seconds >= n) {
	   while (seconds >= n) 
	{
		++(c);
		seconds = seconds - n;
	}
	
	return c;
	} else {
	return c;
	}
  }
  yearC = divide(yearN, yearC);
  dayC = divide(dayN, dayC);
  hourC = divide(hourN, hourC);
  minuteC = divide(minuteN, minuteC);

  printf("%d seconds is %d years, %d days, %d hours, %d minutes, and %d seconds. \n", pSeconds, yearC, dayC, hourC, minuteC, seconds);

  exit(0);

}
