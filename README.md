### 运行项目

```
git clone 仓库地址

导入 IDEA 展开maven工具栏 点击刷新按钮 加载依赖

根目录命令行运行 
mvn flyway:migrate 
初始化 h2数据库

然后点击绿色运行按钮即可

如果是 master 分支代码是有 redis 部分的所以要额外启动 redis 我这里用的docker 一句话完美运行
docker run -p 6379:6379 --name some-redis -d redis
```