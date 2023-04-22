# Chatbot

## turms-plugin-rasa

### Introduction

turms-plugin-rasa is a plugin implementation of the turms-service chatbot based on the open-source conversational AI framework [Rasa](https://rasa.com).

The workflow of turms-plugin-rasa is simple: it forwards messages sent by users to the Rasa server, and then sends the response returned by the Rasa server to the user in the form of a message.

### Installation

- [Installation of the Rasa server](https://rasa.com/docs/rasa/installation/installing-rasa-open-source)
- [Methods for loading plugins](https://turms-im.github.io/docs/server/development/plugin#loading-plugins)

### Configuration

| Configuration Item                                   | Default Value                                 | Description                                                  |
| ---------------------------------------------------- | --------------------------------------------- | ------------------------------------------------------------ |
| turms-plugin.rasa.enabled                            | true                                          | Whether to activate the plugin                               |
| turms-plugin.rasa.instances[?].chatbot-user-id       | 0                                             | When a user sends a message to this user ID, the message is forwarded to the Rasa server |
| turms-plugin.rasa.instances[?].url                   | `http://localhost:5005/webhooks/rest/webhook` | The address of the Rasa server that receives user messages   |
| turms-plugin.rasa.instances[?].request.timeoutMillis | 60_000                                        | Request timeout duration (in milliseconds)                   |
| turms-plugin.rasa.instances[?].response.format       | `PLAIN`                                       | When set to `PLAIN`, the `text` field in the response from the Rasa server will be sent directly to the user as a message;<br />When set to `JSON`, the response from the Rasa server will be first serialized into JSON format text, and then sent to the user as a message. See below for the specific JSON format. |
| turms-plugin.rasa.instances[?].response.delimiter    | `\n`                                          | When `format` is set to `PLAIN` and the user sends one message to the Rasa server but the Rasa server returns multiple responses, the specified string will be used as the delimiter between the `text` fields in the responses. |
| turms-plugin.rasa.instances[?].response.persist      | `DEFAULT`                                     | Whether to persist messages generated based on the responses of the Rasa server.<br />If set to `TRUE`, it means persisting;<br />If set to `FALSE`, it means not persisting;<br />If set to `DEFAULT`, it means judging based on the property `turms.service.message.persist-message`. |

The JSON format of the message sent to the user is:

```json
[
    {
        "text": <string>,
        "image": <string>
    },
    ...
]
```