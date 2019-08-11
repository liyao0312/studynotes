# 怎么用Git写书

## 安装环境

### 第一步 安装node npm

先检测自己电脑是否安装了node npm
```
    # 查看 node 版本
    node -v
     
    # 查看 npm 版本
    npm -v
    复制代码
```
如果成功打印出版本号，说明你本地具备了 node 的运行环境（安装 node 默认安装 npm），而如果没有或报错，则你需要去 node 官网进行 node 的下载及安装，如图：
![](https://img2018.cnblogs.com/blog/1237308/201908/1237308-20190811125923373-429502624.png)
左边的版本是推荐安装的稳定版本，也就是目前已经被正式列入标准的版本，而右边的版本是当前最新的版本，该版本包含了一些新的特性，还未被完全列入标准，可能以后会有所变动。这里建议大家安装最新的 node 稳定版进行开发。


###  第二步 全局安装 gitbook-cli
建议使用 npm 淘宝源：
```
npm config set registry https://registry.npm.taobao.org/

```
然后安装gitbook -cli
```
npm i -g gitbook-cli
```

### 第三部 初始化电子书
```
# 创建一个目录，进入
mkdir gitbook-demo
cd gitbook-demo
 
# 初始化电子书目录
gitbook init 
 
# 编译电子书
gitbook serve 
复制代码
```

### 说明一下：

init 以后，目录里会有这两个文件 README.md 和 SUMMARY.md，README.md 是对电子书的简单介绍，SUMMARY.md 是电子书的目录结构。

目录结构长这样：
```
* [电子书名称](README.md)
* [第一章](chapter1/README.md)
    * [xxxx](chapter1/section1.1.md)
    * [xxxx](chapter1/section1.2.md)
* [第二章](chapter2/README.md)
    * [xxxx](chapter2/section2.1.md)
    * [xxxx](chapter2/section2.2.md)
复制代码
```
编写 SUMMARY.md，执行 gitbook init 生成目录结构文件，然后编写各个文件夹中生成的文件。

最后 gitbook serve。

gitbook serve 命令实际上会首先调用 gitbook build 编译书籍，完成以后会打开一个 web 服务器，监听在本地的 4000 端口。

如果当前书籍写完了，想要发布到自己的网站的话，也可以使用命令输出成html文件使用
```
gitbook build [书籍路径] [输出路径]
```

最后搭配git一起使用时，只需要在书籍根目录下执行
git init
创建一个git仓库，然后就愉快的拉取-书写-提交循环即可了。

别忘了在提交的时候忽略掉module文件夹，不然每次提交拉取的时候会很痛苦....

### 小拓展
你可以将电子书提交到 github，在托管电子书的仓库建一个 gh-pages 分支，将本地编译好的电子书文件（项目根目录下的 _book 目录里的文件 ）上传到这个分支，然后就可以使用这个网址访问 http://yourUserName.github.io/bookName。

当然也可以发布到gitbook，然而这个网站访问有点慢-.-