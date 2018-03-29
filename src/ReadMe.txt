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
    
    2: 创建UsersDaoImpl必须继承BaseDaoImpl<T>
    
    3: 创建UserServiceImpl 必须依赖 UsersDaoImpl
    
    4: 创建junit基于Service的测试用例.
    
    5: 创建UserServlet 必须依赖 UsersServiceImpl(doPost方法中调用登录验证的方法,如果登录成功用户数据存储到session中)
    
                   添加访问servlet的注解：@WebServlet(urlPatterns="/UsersServlet")
   
    6： 创建login.jsp登录页面
    
    7: 创建admin文件夹,此文件夹是管理员才能访问
    
    8: 创建LoginFilter过滤器，对session进行登录判断，记得配置过滤URL-->@WebFilter(urlPatterns = "/admin/*")
    
	Request生命周期: 对于每个用户来说,每一次请求都是一个Request对象.重定向会产生新的Request对象
	Session生命周期: 默认情况与服务器Session交互超过30分钟,则Session自动销毁(Tomcat重启并不会丢失Session)    
     
    9: 登录功能实现完毕后,需要给User类实现Serializable接口.在Tomcat停止后查看work目录.通过修改UID.反序列失败测试得知：反序列化是通过：UID来确定类型的唯一性
     
    10: 讲解ServletContextListener,此Web组件是单例模式.而且在项目启动时候就加载.特别适合用来初始化数据.(公共数据则可以存储到全局唯一application内置对象中)
    
    
    
    
Spring环境搭建,IOC演示

    1: 需要jar + xml配置文件.xml中定义xsd.xml 与jar版本匹配
    
    2: 在xml中配置bean标签来管理类，默认是配置文件加载的时候就会创建Bean,且单例
         
          2.1： lazy-init="true": 懒汉模式
          2.2: scope="prototype" 则可以设置为多例
          
    3: 可以通过set和构造来进行属性赋值.(IOC依赖注入)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    