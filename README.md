<p align="center">
hmtool springboot版demo
</p>
<p align="center">
-- 主页：<a href="http://mhuang.tech/hmtool-parent">http://mhuang.tech/hmtool-parent</a>  --
</p>
<p align="center">
    -- QQ群①:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=6703688b236038908f6c89b732758d00104b336a3a97bb511048d6fdc674ca01"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="hmtool官方交流群①" title="hmtool官方交流群①"></a>
</p>
---------------------------------------------------------------------------------------------------------------------------------------------------------

## 简介
该项目是hmtool与springboot集成的crud 案例管理系统
## 技术
- 前端
    + H+
    + bootstrap3+
    + jquery2+
- 后台
    + springboot2.1.8+
    + hmtool-ext-interchan 
    + elasticsearch 6+
    + kafka 2.11+
    + jdk1.8+
    + swagger2
    
## 项目结构
- inter-boot-demo(parent)
    + inter-boot-protocol(协议包、存放DTO,QVO,RVO等)
    + inter-boot-sso(用户角色菜单管理)
    + inter-logger-common(日志通用包)
    + inter-boot-web(前端网页)
    
## 案例运行步骤
- 安装软件环境

        elasticsearch6+，kibana6+（用于查看日志情况)，mysql5.7+(存储)，jdk1.8+,kafka2.1+(消息消费)
- 创建mysql数据库
        
        sql脚本存放在doc/数据库文件/inter-boot-demo.sql、先创建数据库、在选择数据库指定sql进行导入、若与demo不一致请自行配置相关ip、port、用户名、密码等
- 启动SSO
- 启动web
- 打开浏览器即可
>>>>>>> master
