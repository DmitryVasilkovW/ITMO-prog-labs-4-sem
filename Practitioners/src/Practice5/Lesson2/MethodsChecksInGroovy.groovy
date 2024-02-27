package Practice5.Lesson2

def num1 = new BigDecimal("10")
def num2 = new BigDecimal("10")


println "Do the variables point to the same location in memory: ${num1.is(num2)}"


println "Are the values of the variables equal to: ${num1.equals(num2)}"


def sum = num1.add(num2)
def div = num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP)
def mult = num1.multiply(num2)

println "Sum: $sum"
println "Dividing: $div"
println "Multiplication: $mult"

def sum1 = num1 + num2
def div1 = num1 / num2
def mult1 = num1 * num2

println "Sum: $sum1"
println "Dividing: $div1"
println "Multiplication: $mult1"