package Tasks.Practitioners.Practice5.Lesson2

import groovy.time.TimeCategory

def date1 = Date.parse('yyyy-MM-dd', '2015-02-28')
def date2 = Date.parse('yyyy-MM-dd', '2015-01-31')


def daysBetween = date1 - date2
println "Difference in days between February 28, 2015 and January 31, 2015: ${daysBetween} days"


def date3 = date1 - 30
println "Date after subtracting 1 month from February 28, 2015: ${date3.format('yyyy-MM-dd')}"


def date4 = date3 + 1 + 30
println "Date after adding 1 day and 1 month to the previous date: ${date4.format('yyyy-MM-dd')}"

use(TimeCategory) {
    date4 = date3 + 1.day + 1.month
}
println "Date after adding 1 day and 1 month to the previous date: ${date4.format('yyyy-MM-dd')}"
