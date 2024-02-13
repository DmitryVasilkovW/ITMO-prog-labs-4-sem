package Tasks.Practitioners.Practice5.Lesson3

def divide = { a, b ->
    if (b == 0)
    {
        throw new ArithmeticException("Division by zero is unacceptable")
    }
    else
    {
        a / b
    }
}

println divide(24, 1)
println divide(23, 9)
