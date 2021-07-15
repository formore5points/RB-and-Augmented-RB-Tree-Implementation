public class Date {
	private String Day;
	private String Month;
	private String Year;

	public Date(String day, String month, String year) {
		Day = day;
		Month = month;
		Year = year;
	}

	public int getDay() {
		return Integer.parseInt(Day);
	}

	public void setDay(String day) {
		Day = day;
	}

	public int getMonth() {
		return Integer.parseInt(Month);
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getYear() {
		return Integer.parseInt(Year);
	}

	public void setYear(String year) {
		Year = year;
	}

}
