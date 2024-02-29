#python面向对象：
      #类的继承，创建类是再类名()里注明
      #类的实例化，变量名=类名（）
      #类里面方法如果想调用类里的属性，方法，需要传入self，
      #类方法，方法前备注@classmethon,还有类属性，相当于java里被static修饰。
class cat(animal):  #cat类继承animal类
      count = 1  #类属性，可以直接被类调用
      #self 必须写,name为类里面的属性
           
     def  __init__(self,name):
         self.name = name

      def aa():
            print('没有传self的aa方法')

      def bb(self):
            print('传self的bb方法，bb方法要调用aa方法')
            self.aa()

      @classmethon 
      def cc():        #类方法
           print(cat.count)
           
