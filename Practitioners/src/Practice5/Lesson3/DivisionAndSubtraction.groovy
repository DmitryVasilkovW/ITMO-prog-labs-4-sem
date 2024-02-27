package Practice5.Lesson3

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

def subtract = { d, c -> d - c }

def calculate = { a, b, c -> subtract(divide(a, b), c) }

println calculate(2, 3, 9)