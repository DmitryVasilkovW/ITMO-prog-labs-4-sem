package Practice5.Lesson1.VerificationOfWorkWithConstructors

class FirstClassWithoutConstructor
{
    private int _isu = 0
    private String _name

    def int GetIsu() { return _isu }
    def String GetName() { return _name}

    void InitName(String name)
    {
        if (_name == null)
        {
            _name = name
        }
    }

    void InitIsu(int isu)
    {
        if (_isu = 0)
        {
            _isu = isu
        }
    }
}
