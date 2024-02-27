package Practice5.Lesson1.VerificationOfWorkWithConstructors

import groovy.lang.Binding

firstclass = new FirstClass(223399, "Mishel Ganin")
firstclasswithoutconstructor = new FirstClassWithoutConstructor(_isu : 239239, _name : "Misha Ganin")
// firstclass = new FirstClass(_isu : 239239, _name : "Misha Ganin")

print(firstclass.GetName() + " " + firstclass.GetIsu())
print("\n")
print(firstclasswithoutconstructor.GetName() + " " + firstclasswithoutconstructor.GetIsu())
print("\n")

name = new Binding()
name.setVariable('name', 'Emin')

isu = new Binding()
isu.setVariable('isu', 239241)

secondclass = new FirstClass(isu.getVariable('isu'), name.getVariable('name'))

print(secondclass.GetName() + " " + firstclasswithoutconstructor.GetIsu())