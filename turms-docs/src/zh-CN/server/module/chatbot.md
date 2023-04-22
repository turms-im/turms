# 聊天机器人

## turms-plugin-rasa

### 简介

turms-plugin-rasa是一个基于开源对话式AI框架[Rasa](https://rasa.com)而开发的turms-service聊天机器人实现插件。

turms-plugin-rasa的工作流程很简单，即：将用户发送的消息转发给Rasa服务端，等Rasa服务端返回响应后，Turms服务端再将响应以消息的形式发送给用户。

### 安装

- [Rasa服务端安装](https://rasa.com/docs/rasa/installation/installing-rasa-open-source)
- [插件的加载方式](https://turms-im.github.io/docs/zh-CN/server/development/plugin#插件加载方式)

### 配置

| 配置项                                               | 默认值                                        | 说明                                                         |
| ---------------------------------------------------- | --------------------------------------------- | ------------------------------------------------------------ |
| turms-plugin.rasa.enabled                            | true                                          | 是否启动插件                                                 |
| turms-plugin.rasa.instances[?].chatbot-user-id       | 0                                             | 当用户发送消息给该用户ID时，将消息转发给Rasa服务端           |
| turms-plugin.rasa.instances[?].url                   | `http://localhost:5005/webhooks/rest/webhook` | 用于接收用户消息的Rasa服务端地址                             |
| turms-plugin.rasa.instances[?].request.timeoutMillis | 60_000                                        | 请求超时时长（毫秒）                                         |
| turms-plugin.rasa.instances[?].response.format       | `PLAIN`                                       | 为`PLAIN`时，Rasa服务端响应中的`text`文本字段将会被直接作为消息，发送给用户；<br />为`JSON`时，Rasa服务端响应会先被序列化成JSON格式文本，再作为消息，发送给用户。JSON具体格式见下文。 |
| turms-plugin.rasa.instances[?].response.delimiter    | `\n`                                          | 当上述`format`为`PLAIN`，且用户发送给Rasa服务端一条消息，而Rasa服务端返回多个响应时，使用该字符串作为响应`text`文本字段之间的分隔符 |
| turms-plugin.rasa.instances[?].response.persist      | `DEFAULT`                                     | 是否存储基于Rasa服务端响应生成的消息。<br />为`TRUE`时，表示存储；<br />为`FALSE`时，表示不存储；<br />为`DEFAULT`时，表示基于属性`turms.service.message.persist-message`判断； |

发送给用户的消息的JSON文本格式为：

```json
[
    {
        "text": <string>,
        "image": <string>
    },
    ...
]
```