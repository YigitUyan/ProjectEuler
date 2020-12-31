// Project Euler - Question 19:
//   You are given the following information, but you may prefer to do some research for yourself.
//   - 1 Jan 1900 was a Monday.
//   - Thirty days has September, April, June and November.
//     All the rest have thirty-one, Saving February alone,
//     Which has twenty-eight, rain or shine. And on leap years, twenty-nine.
//   - A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
// How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

public class Question19 {

    public static void main(String[] _Arguments) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] currentDate = {1, 1, 1900};
        int[] targetDate = {31, 12, 2000};

        int trackDays = 1;
        int counter = 0;

        if (currentDate[2] % 4 == 0 && (currentDate[2] % 100 != 0 || currentDate[2] % 400 == 0))
            months[1] = 29;

        while (currentDate[0] < targetDate[0] || currentDate[1] < targetDate[1] || currentDate[2] < targetDate[2]) {
            if (currentDate[2] != 1900 && currentDate[0] == 1 && trackDays == 7)
                counter++;

            trackDays++;
            if (trackDays > 7)
                trackDays = 1;

            currentDate[0]++;
            if (currentDate[0] > months[currentDate[1] - 1]) {
                currentDate[0] = 1;
                currentDate[1]++;
                if (currentDate[1] > 12) {
                    currentDate[1] = 1;
                    currentDate[2]++;
                    if (currentDate[2] % 4 == 0 && (currentDate[2] % 100 != 0 || currentDate[2] % 400 == 0))
                        months[1] = 29;
                    else
                        months[1] = 28;
                }
            }
        }

        System.out.println("Answer: " + counter);
    }
}
