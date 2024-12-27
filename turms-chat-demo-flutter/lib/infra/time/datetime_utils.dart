class DateTimeUtils {
  DateTimeUtils._();

  static DateTime getFirstDateOfTheWeek(DateTime dateTime) =>
      dateTime.subtract(Duration(days: dateTime.weekday - 1));

  static DateTime getMostRecentWeekday(DateTime date, int weekday) =>
      DateTime(date.year, date.month, date.day - (date.weekday - weekday) % 7);

  static bool isBetween(DateTime date, DateTime? start, DateTime? end) =>
      (start != null && date.isAfter(start)) &&
      (end != null && date.isBefore(end));
}
