package Tasks.Practitioners.Practice5.Lesson2

import java.time.LocalDate
import java.time.temporal.ChronoUnit

LocalDate date1 = LocalDate.of(2015, 2, 28)
LocalDate date2 = LocalDate.of(2015, 1, 31)


long daysBetween = ChronoUnit.DAYS.between(date2, date1)
println "Difference in days between February 28, 2015 and January 31, 2015: $daysBetween days"


LocalDate date3 = date1.minusMonths(1)
println "Date after subtracting 1 month from February 28, 2015: $date3"


LocalDate date4 = date3.plusDays(1).plusMonths(1)
println "Date after adding 1 day and 1 month to the previous date: $date4"
