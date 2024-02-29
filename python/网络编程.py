''' tcp 与 udp
区别：tcp基于有连接，udp基于无连接
      对系统资源的要求（TCP较多，UDP少）
      TCP保证数据正确性，UDP可能丢包，TCP保证数据顺序，UDP不保证。
      所以tcp可靠，udp不可靠。
      TCP面向字节流，实际上是TCP把数据看成一连串无结构的字节流;UDP是面向报文的
      UDP没有拥塞控制，因此网络出现拥塞不会使源主机的发送速率降低
      TCP首部开销20字节;UDP的首部开销小，只有8个字节
      TCP是1对1  UDP 支持支持一对一，一对多，多对一和多对多的交互通信
'''
#tcp的三次握手与四次挥手 https://blog.51cto.com/jinlong/2065461
 #网络编程我觉得需要一个系统性学习才能明白，所以这里就写一些基本编程流程
import socket

def service_client(new_socket):
    """为这个客户端返回数据"""

    # 1. 接收浏览器发送过来的请求 ，即http请求
    # GET / HTTP/1.1
    # .....
    request = new_socket.recv(1024)  #这个必须有，不如可能会导致浏览器刷不出数据来

    # 2. 返回http格式的数据，给浏览器
    # 2.1 准备发送给浏览器的数据---header
    date = "HTTP/1.1 200 OK\r\n"  #浏览器必须要有这个响应头
    date += "\r\n"
    # 2.2 准备发送给浏览器的数据---boy
    date += "hahahhah"
    new_socket.send(date.encode("utf-8"))#发生给浏览器必须要经过encode将它编码成字节文件。
    # 关闭套接字
    new_socket.close()



def main():#服务器端流程
    #创建套接字
        #创建udp套接字
    udp_socket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        #创建tcp套接字
    tcp_socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    #绑定IP 与端口号
    tcp_socket.bind(('192.168.43.205',7878))
    # 3. 变为监听套接字
    tcp_socket.listen(128)
    while True:
         # 4. 等待新客户端的链接
        new_socket, client_addr = tcp_socket.accept()
         # 5. 为这个客户端服务
        service_client(new_socket)
        # 6. 关闭socket
        new_socket.close()  #老师会在客户端服务哪里关闭，但我觉得在这里关闭好，因为在利用多线程，进程时可用减少好多麻烦
    # 关闭监听套接字
    tcp_socket.close()
