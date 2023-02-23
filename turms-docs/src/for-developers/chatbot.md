# 聊天机器人

## turms-plugin-rasa

### 简介

turms-plugin-rasa是一个基于开源对话式AI框架[Rasa](https://rasa.com)而开发的turms-service聊天机器人实现插件。

turms-plugin-rasa的工作流程很简单，即：将用户发送的消息转发给Rasa服务端，等Rasa服务端返回响应后，Turms服务端再将响应以消息的形式发送给用户。

### 安装

- [Rasa服务端安装](https://rasa.com/docs/rasa/installation/installing-rasa-open-source)
- [插件的加载方式](https://turms-im.github.io/docs/for-developers/plugin.html#插件加载方式)

### 配置

| 配置项                                         | 默认值                                        | 说明                                                         |
| ---------------------------------------------- | --------------------------------------------- | ------------------------------------------------------------ |
| turms-plugin.rasa.enabled                      | true                                          | 是否启动插件                                                 |
| turms-plugin.rasa.response-delimiter           | `\n`                                          | 当用户发送给Rasa服务端一条消息，且Rasa返回多个响应时，使用该字符串作为响应文本之间的分隔符 |
| turms-plugin.rasa.instances[?].chatbot-user-id | 0                                             | 当用户发送消息给该用户ID时，将消息转发给Rasa服务端           |
| turms-plugin.rasa.instances[?].url             | `http://localhost:5005/webhooks/rest/webhook` | 用于接收用户消息的Rasa服务端地址                             |