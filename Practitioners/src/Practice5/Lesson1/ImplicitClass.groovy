package Practice5.Lesson1

class ImplicitClass
{
    def implicitVar = "Hello, World!"

    void PrintVar()
    {
        def implicitVar = "Hello, Groovy!"
        println(this.implicitVar)
    }
}
