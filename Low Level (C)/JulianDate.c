#include <stdio.h>
#include <stdlib.h>

#define Jan 1
#define Feb 2
#define Mar 3
#define Apr 4
#define May 5
#define Jun 6
#define Jul 7
#define Aug 8
#define Sep 9
#define Oct 10
#define Nov 11
#define Dec 12

#define LongMonth 31
#define ShortMonth 28
#define LeapMonth 29
#define MediumMonth 30

#define LeapYear 366
#define Year 365

//Prototype

void get_data(int *year, int *dayOfYear);
int has_error(int year, int dayOfYear);
void convert_Julian_date(int year, int dayOfYear, int *month, int *day);
int days_in_a_Month(int month, int year);
int leap_year(int year);
void output_data(int year, int dayOfYear, int month, int day);

//Start of functions

int main(void) {

        int year = 0, dayOfYear = 0, month = 0, day = 0;
        get_data(&year, &dayOfYear);
        convert_Julian_date(year, dayOfYear, &month, &day);
        output_data(year, dayOfYear, month, day);

}

void convert_Julian_date(int year, int dayOfYear, int *month, int *day) {

        int sum = 0;
        do {
                (*month)++;
                sum += days_in_a_month(year, *month);
               // (*month)++;	
        }while(sum < dayOfYear);
	
        *day = days_in_a_month(year, *month) - (sum - dayOfYear);
       // printf("%d \n", (sum - dayOfYear));
	//*day = (sum - dayOfYear);
}

int days_in_a_month(int year, int month) {
        switch(month) {
        case Jan:
                return LongMonth;
        case Feb:
                if(leap_year(year)){
                        return LeapMonth;
                } else {return ShortMonth;
                }
        case Mar:
                return LongMonth;
        case Apr:
                return MediumMonth;
        case May:
                return LongMonth;
        case Jun:
                return MediumMonth;
        case Jul:
                return LongMonth;
        case Aug:
                return LongMonth;
        case Sep:
                return MediumMonth;
        case Oct:
                return LongMonth;
        case Nov:
                return MediumMonth;
        case Dec:
                return LongMonth;
        }
}

void get_data(int *year, int *dayOfYear){

        do {
                printf("Enter a year and the day of the year\n");
                scanf("%d%d", year, dayOfYear);
        }while(has_error(*year, *dayOfYear));
}

int has_error(int year, int dayOfYear){
        if (year < 1 || dayOfYear < 1){
                printf("Error\n");
                return 1;
        }
        return 0;
}

void output_data(int year, int dayOfYear, int month, int day){

      //  printf("%d %d %d %d", year, dayOfYear, month, day);
          printf("The %d day of %d is %d %d, %d", dayOfYear, year, month, day, year); 
}

int leap_year(int year) {
        if ((year%4 == 0) && ((year%100 != 0) || (year%400 == 0))){
                return 1;
                        } else {
                return 0;
                }
}
