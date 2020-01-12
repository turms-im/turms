### Quick Start

- 下载并解压[Turms服务端](https://github.com/turms-im/turms/releases)压缩包。

- 下载并安装[MongoDB v4.2.2](https://www.mongodb.com/download-center/community)，并将数据库的URI、账号与密码配置到config/application.yaml中（提醒：您也可以直接在此处配置多个MongoDB地址，Turms会自动接入MongoDB集群）。

  （具体可参考：https://docs.mongodb.com/manual/reference/connection-string/）

- 运行MongoDB数据库。运行bin/mongo-quick-start-run-primary与bin/mongo-quick-start-run-secondary，约几秒后会提示“waiting for connections on port 29510”。则再运行bin/mongo-quick-start-init（或者自行搭建MongoDB集群）
  请确保运行正确，否则Turms会抛出MongoSocketOpenException、MongoNotPrimaryException等异常。

- （可跳过，保持默认）根据您的需求配置config/hazelcast.yaml、config/jvm.options、config/application.yaml（您可以在此处配置Turms自定义的配置参数）。

- 在所有需要运行Turms服务端的系统上，运行bin/turms脚本。若您保留默认的config/hazelcast.yml配置，Turms服务端会自动寻找其他Turms服务端节点，由此Turms集群开始运作。

提醒：上述操作主要用于您初次体验Turms集群使用，若您需将Turms部署在生产环境当中，请务必查阅Wiki手册，了解各种配置参数的意义，以最小的资源消耗，来定制属于您自己的业务需求与业务组合。

Turms服务端会自动检测Turms集群中是否已存在一个角色为ROOT，且名称为“turms”的管理员账号。如果不存在，则Turms服务端会自动创建一个角色为ROOT、名称为“turms”且带有随机密码的管理员账号，并将该账号打印在日志中，如：

```
2019-10-13 00:00:00.000  INFO 14152 --- [tLoopGroup-2-20] im.turms.turms.common.TurmsLogger:
Root admin: {
	"Account" : "turms",
	"Raw Password" : "4CWBq&z&BdLDe@77q+]g +MHLV#Vx~[i"
}
```