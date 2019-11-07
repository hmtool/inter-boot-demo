<<<<<<< HEAD
# inter-boot-demo

#### 介绍
springboot版hmtool集成demo

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
=======
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
