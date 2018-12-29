package objectMethods;

public class Date {
    private final int month;
    private final int day;
    private final int year;

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Date uddaDate = (Date) o;

        if (month != uddaDate.month) {
            return false;
        }
        if (day != uddaDate.day) {
            return false;
        }
        if (year != uddaDate.year) {
            return false;
        }
        return true;
    }


}