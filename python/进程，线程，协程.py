'''线程属于进程，同一进程所产生的线程共享同一内存空间，
当进程退出时该进程所产生的线程都会被强制退出并清除,
而进程与进程之间一般是数据不共享 需要共享时， multiprocessing提供了两种方式。
数据可以用Value或Array存储在一个共享内存地图里'''

  ###线程###
'''线程是程序执行过程中的最小单元
Threading用于提供线程相关的操作，线程是应用程序中工作的最小单元。
threading 模块建立在 _thread 模块之上。thread 模块以低级、原始的方式来处理和控制线程，
而 threading 模块通过对 thread 进行二次封装，提供了更方便的 api 来处理线程。'''

import threading
import time
def worker(num):
    """
    thread worker function
    :return:
    """
    time.sleep(1)
    print("The num is  %d" % num)
    # Thread方法说明
    # t.start() : 激活线程，
    # t.getName() : 获取线程的名称
    # t.setName() ： 设置线程的名称
    # t.name : 获取或设置线程的名称
    # t.is_alive() ： 判断线程是否为激活状态
    # t.isAlive() ：判断线程是否为激活状态

 # for i in range(20):
 #    t = threading.Thread(target=worker,args=(i,),name="t.%d % i")
 #    t.start()
#线程锁threading.RLock和threading.Lock
    '''由于线程之间是进行随机调度，
    并且每个线程可能只执行n条执行之后，CPU接着执行其他线程。
    为了保证数据的准确性，引入了锁的概念'''
lock = threading.RLock() #lock对象
globals_num = 0 #一个全局变量
def worker_1():
    global globals_num  #表示调用全局变量
    globals_num+=1
    time.sleep(1)
    print("it is func The globals_num is  %d" %globals_num)

def worker_2(num):
    lock.acquire() #获得锁，锁定调取的资源
    global globals_num  #表示调用全局变量
    globals_num+=1
    time.sleep(1)
    print("it is %d func The globals_num is  %d" %(num+1, globals_num))
    lock.release() #释放锁
    #RLock允许在同一线程中被多次acquire。而Lock却不允许这种情况。 如果使用RLock，那么acquire和release必须成对出现，
    # 即调用了n次acquire，必须调用n次的release才能真正释放所占用的琐。
# for i in range(10):
#     t = threading.Thread(target=worker_1,args=(i,))
#     t.start()
#线程池  queue队列
# import queue
# import threading
# import contextlib
# import time
# StopEvent = object()  #不懂
# class ThreadPool(object):
#     def __init__(self, max_num):
#         self.q = queue.Queue()
#         self.max_num = max_num
#
#         self.terminal = False
#         self.generate_list = []
#         self.free_list = []
#
#     def run(self, func, args, callback=None):
#         """
#         线程池执行一个任务
#         :param func: 任务函数
#         :param args: 任务函数所需参数
#         :param callback: 任务执行失败或成功后执行的回调函数，回调函数有两个参数1、任务函数执行状态；2、任务函数返回值（默认为None，即：不执行回调函数）
#         :return: 如果线程池已经终止，则返回True否则None
#         """
#         if len(self.free_list) == 0 and len(self.generate_list) < self.max_num:
#             self.generate_thread()
#         w = (func, args, callback,)
#         self.q.put(w)
#
#     def generate_thread(self):
#         """
#         创建一个线程
#         """
#         t = threading.Thread(target=self.call)
#         t.start()
#
#     def call(self):
#         """
#         循环去获取任务函数并执行任务函数
#         """
#         current_thread = threading.currentThread
#         self.generate_list.append(current_thread)
#
#         event = self.q.get()  # 获取线程
#         while event != StopEvent:   # 判断获取的线程数不等于全局变量
#
#             func, arguments, callback = event   # 拆分元祖，获得执行函数，参数，回调函数
#             try:
#                 result = func(*arguments)   # 执行函数
#                 status = True
#             except Exception as e:    # 函数执行失败
#                 status = False
#                 result = e
#
#             if callback is not None:
#                 try:
#                     callback(status, result)
#                 except Exception as e:
#                     pass
#
#             # self.free_list.append(current_thread)
#             # event = self.q.get()
#             # self.free_list.remove(current_thread)
#             with self.work_state():
#                 event = self.q.get()
#         else:
#             self.generate_list.remove(current_thread)
#
#     def close(self):
#         """
#         关闭线程，给传输全局非元祖的变量来进行关闭
#         :return:
#         """
#         for i in range(len(self.generate_list)):
#             self.q.put(StopEvent)
#
#     def terminate(self):
#         """
#         突然关闭线程
#         :return:
#         """
#         self.terminal = True
#         while self.generate_list:
#             self.q.put(StopEvent)
#         self.q.empty()
#
#     @contextlib.contextmanager
#     def work_state(self):
#         self.free_list.append(threading.currentThread)
#         try:
#             yield
#         finally:
#             self.free_list.remove(threading.currentThread)
# def work(i):
#     print(i)
#     return i +1 # 返回给回调函数
#
# def callback(ret):
#     print(ret)
#
# pool = ThreadPool(10)
# for item in range(50):
#     pool.run(func=work, args=(item,),callback=callback)
# pool.terminate()
# pool.close()



    ###进程###
'''multiprocessing模块,在multiprocessing中，通过创建Process对象生成进程，然后调用它的start()方法，'''
import multiprocessing
   ##进程池##
from  multiprocessing import Pool

def f1(i):
    time.sleep(0.5)
    print(i)
    return i + 100
def f2(arg):
    print(arg)
#apply
# if __name__ == "__main__":
#     pool = Pool(5)
#     for i in range(1,31):
#         pool.apply(func=f1,args=(i,))

#apply_async
if __name__ == "__main__":
    pool = Pool(5)
    for i in range(1,31):
        pool.apply_async(func=f1,args=(i,),callback=f2)
    pool.close()
    pool.join()
'''apply(func[, args[, kwds]]) ：使用arg和kwds参数调用func函数，结果返回前会一直阻塞，由于这个原因，apply_async()更适合并发执行，另外，func函数仅被pool中的一个进程运行。
   apply_async(func[, args[, kwds[, callback[, error_callback]]]]) ： apply()方法的一个变体，会返回一个结果对象。如果callback被指定，那么callback可以接收一个参数然后被调用，
            当结果准备好回调时会调用callback，调用失败时，则用error_callback替换callback。 Callbacks应被立即完成，否则处理结果的线程会被阻塞。
    close() ： 阻止更多的任务提交到pool，待任务完成后，工作进程会退出。
    join() : wait工作线程的退出，在调用join()前，必须调用close()这样是因为被终止的进程需要被父进程调用wait（join等价与wait），否则进程会成为僵尸进程。
    '''

    ###协程###
'''线程和进程的操作是由程序触发系统接口，最后的执行者是系统；协程的操作则是程序员。
   协程存在的意义：对于多线程应用，CPU通过切片的方式来切换线程间的执行，线程切换时需要耗时（保存状态，下次继续）。协程，则只使用一个线程，在一个线程中规定某个代码块执行顺序。
   协程的适用场景：当程序中存在大量不需要CPU的操作时（IO），适用于协程；'''

# 协程有3种包提供: asyncio greenlet gevent
#老师讲的是gevent ，因为和线程进程操作很像
import gevent

def fun1():
    print("www.baidu.com")   # 第一步
    gevent.sleep(0)
    #gevent 会做一些封装time.sleep()，因此我们常常调用gevent.sleep()
    #在导入gevent时，如果涉及线程,进程，要执行以下语句解掉gevent的封装
    # from gevent import  monkey
    # monkey.patch_all()
    print("end the baidu.com")  # 第三步

def fun2():
    print("www.zhihu.com")   # 第二步
    gevent.sleep(0)
    print("end th zhihu.com")  # 第四步

gevent.joinall([
    gevent.spawn(fun1),
    gevent.spawn(fun2),
])