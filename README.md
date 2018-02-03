# base-frame-boot
 **框架说明** 

1. 基于springboot+shiro+freemarker的快速开发框架,代码结构清晰，快速上手使用！
2. 配置代码生成器，减少70%开发时间，专注业务逻辑。
3. 前端声明式组件封装、附带文档编写 ctrl+c ctrl+v 即可使用。封装数据源，可通过url、枚举、字典直接渲染组件。代码量极少且易维护。
4. layui常用代码的二次封装，省略layui部分繁琐的代码！
 **java** 


- common     
        - config         配置类 <br>
        - enumresource   枚举类<br>
        - exception      统一异常处理<br>
        - log            统一日志处理<br>
        - shiro          shiro相关<br>
        - utils          工具类<br>
        - xss            xss相关类<br>
       
- controller  

- dao   
   
- entity   
  
- service


 **resources** 

- db.migration     flyway sql脚本

- generator        代码生成器相关配置

- mapper           mapper文件

- static           静态文件

- templates        freemarker页面



 **如何启动**
 
1. 通过git下载源码
2. 创建数据库cy-fast，数据库编码为UTF-8（已配置flyway数据库版本管理，无需创建表）
3. IDEA、Eclipse导入项目
4. 启动 CyFastApplication 类
5. 项目访问路径：http://localhost:8080/
6. 账号密码：admin/admin
