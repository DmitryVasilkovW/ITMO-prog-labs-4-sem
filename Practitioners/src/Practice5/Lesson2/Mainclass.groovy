package Practice5.Lesson2

def checker = new ReturnChecker()

println(checker.returnInteger(null))

try
{
    println(checker.returnInt(null))
}
catch (NullPointerException e)
{
    println("Caught NullPointerException")
}
