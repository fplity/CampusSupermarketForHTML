# 校园超市管理系统 · Spring Boot 版

`CS` 是 Spring Boot 4.1.2 + Servlet/JDBC 后端，`CSF` 保留 HTML/CSS/JavaScript 前端。打包时会把 CSF 一起放入可执行 JAR。网页和接口统一由同一个程序提供，不用单独下载或配置 Tomcat。

## 收到压缩包后运行

1. 安装或使用已有的 **Java 17 或更高版本**，确认 `java -version` 可运行。
2. 启动原来的 MySQL，保留已有的 `cs` 数据库和 `user`、`shopping` 表；程序启动只检查连接和字段，不重建或清空数据库。
3. 解压整个压缩包，双击 `CS/start.cmd`，按提示输入本机 MySQL 密码。默认数据库地址是 `jdbc:mysql://localhost:3306/cs`，用户名为 `root`。
4. 看到应用启动成功后，在浏览器打开 **http://localhost:8080/webapp/**，即可进入登录页。运行窗口需要保持打开。

不要继续使用旧的 IDEA Tomcat 运行配置，也不需要另开 Live Server。`start.cmd` 中输入的是本机 MySQL 密码，不是网站账号的登录密码。

## 自定义数据库或端口

在 PowerShell 中进入 `CS` 文件夹，按需设置以下变量，然后运行 JAR。实际密码只保存在本机环境中，不要提交到 GitHub。

```powershell
$env:DB_URL = 'jdbc:mysql://localhost:3306/cs'
$env:DB_USER = 'root'
$secret = Read-Host 'MySQL password' -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new('', $secret).Password
java -jar app/campus-supermarket.jar
```

如果 8080 被原来启动的 Tomcat 占用，先关闭原来的服务；也可以设置 `$env:SERVER_PORT = '8081'`，然后访问 `http://localhost:8081/webapp/`。使用网页服务提供的页面时，接口会自动使用当前地址和端口。

默认只监听本机 `127.0.0.1`。`DB_URL`、`DB_USER` 和 `DB_PASSWORD` 分别控制数据库地址、用户名和密码。

如果本机还没有表，可以手动执行 `database/schema.sql`。此脚本不包含账号、密码或他人的数据库数据，也不会自动在启动时执行。

## 从源码构建或在 IDEA 中打开

用 IDEA 打开 **CS/pom.xml**，选择作为 Maven 项目导入，然后选择本机 JDK 17 或更高版本。旧的 `.iml` 和个人 `.idea` 配置已经移除。

```powershell
.\mvnw.cmd clean package
java -jar target/campus-supermarket.jar
```

首次构建需要联网下载 Maven 和依赖。收到含 `app/campus-supermarket.jar` 的转发包后，直接运行不需要 Maven 或联网下载依赖。

重新构建前保留 `CS` 与 `CSF` 的相邻关系。前端文件直接维护在 `CSF` 中，Maven 会自动将它们打入 JAR，不必重复复制。

## 接口兼容

- `/webapp/user?op=register`：注册，成功返回 `1`，账号等唯一字段重复返回 `0`。
- `/webapp/user?op=login`：登录，返回 `true` 或 `false`。
- `/webapp/shopping?op=getAll`、`search`、`add`、`update`、`delete`：保留原商品接口。

数据库和网站账号沿用原项目；迁移到 Spring Boot 不会重置数据。
