# turms-admin

turms-admin is a customized backend administration single page application (SPA) for Turms project, specifically including: cluster management (cluster monitoring, cluster configuration), content management, client blacklist, permission control, client terminal, these five major sections.

Note: turms-admin is positioned only as a visual Web application for the Turms server-side Admin API, so turms-admin itself does not provide any data collection, data analysis and alarm functions.

## Deployment Overview

Turms uses a separate front- and back-end design, so the Turms server is not even "aware" of the existence of the `turms-admin` front-end project. So users can even open turms-admin directly in the browser and interact with the Turms server through local static HTML files. However, in order to facilitate developers' operation and deployment, the turms-admin project also provides the following two deployment options.

### Docker image (recommended)

```shell
docker run -d -p 6510:6510 ghcr.io/turms-im/turms-admin
```

The image provides static resources for turms-admin externally through the built-in Nginx server. You will be able to access the [http://localhost:6510](http://localhost:6510) page after running the command

### Simple web server

The turms-admin project itself also provides a simple web server based on `Node.js`. This web server will provide static resources of turms-admin to the public via HTTP interface, and will carry PM2 for turms-admin process management by default.

#### Installation and Implementation Steps

1. Install [Node.js](https://nodejs.org/en)
2. In the `turms-admin` directory, execute the `npm run quickstart` command, which consists of `npm install && npm run build && npm run start`, including the dependency package installation, front-end build and server-side execution. Wait for PM2 to indicate that the status of turms-admin is `online`, indicating that the turms-admin server-side process has been started
3. Open the browser and visit the [http://localhost:6510](http://localhost:6510) page

#### Common operations and maintenance commands

start: Execute the turms-admin server-side process

stop: Terminate the turms-admin server process

delete：Terminate the turms-admin server process and delete its process record in PM2

restart: restart the turms-admin server

reload: reload the turms-admin server configuration

For more commands and server-side configurations, please refer to [PM2 documentation](https://pm2.keymetrics.io/docs/usage/pm2-doc-single-page)

## Introduction of the module

Cluster management.

* Cluster monitoring: view the real-time operational status of the cluster; view the specific information and metric data of a particular server
* Cluster Configuration: This section corresponds to the global configuration function of the Turms server, which can modify the Turms server configuration in real time with zero downtime
* Cluster Flight Logger: Manage the flight logger of each node of the cluster
* Cluster plug-in: manage the plug-in of each node of the cluster

Content management: add, delete, change and check various business data

Client Blacklist: This part corresponds to the global blacklist mechanism of Turms server, which is used to add, delete, and check blacklist records

Permission control: used to add, delete and change the information and permissions of administrators

Client terminal: equipped with `turms-client-js` client implementation, used for administrators to quickly test the real client request and server response

TODO: post GIF demo image