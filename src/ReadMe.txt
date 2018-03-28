常见Java技术架构：
linux + mysql + tomcat + spring + mybaits + mvc
linux + oracle + weblogic + spring + hibernate + struts

常见web错误：
1、localhost拒绝了我们的访问：端口号不对或服务未启动
2、404说明Tomcat已经启动成功，但请求地址不正确
3、500说明Tomcat已经启动成功且资源正确，但是解析时抛出异常

改了Java代码，又Tomcat依赖于JDK，则需要重启Server

获取前端数据参数，调用业务逻辑方法，返回前端页面结果


 登录实现步骤:
    
    1: 创建表 --》 创建相应Model类(Users)
    
    2: 创建UsersDaoImpl必须继承BaseDaoImpl
    
    3: 创建UserServiceImpl 必须依赖 UsersDaoImpl
    
    4: 创建junit基于Service的测试用例.
    
    5: 创建UserServlet 必须依赖 UsersServiceImpl(doPost方法中调用登录验证的方法,如果登录成功用户数据存储到session中)
    
                   添加访问servlet的注解：@WebServlet(urlPatterns="/UsersServlet")
   
    6： 创建login.jsp登录页面
    
    7: 创建admin文件夹,此文件夹是管理员才能访问