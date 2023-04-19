# Build and Start

## Automatically Build and Start

### Stand-alone Environment

Applicable scenarios: The construction process is convenient and fast, but it cannot meet the requirements of disaster recovery, elastic expansion, zero downtime upgrade, and load balancing. It is mainly used to build demos for display and serve users who do not require SLA.

#### Based on Docker Compose

Through the following commands, a complete set of Turms minimal cluster (including turms-gateway, turms-service and turms-admin) and its dependent servers (MongoDB shard cluster and Redis) can be built automatically

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions
# Or "ENV=dev,demo docker compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate -d" to run with sidecar services in dev profile
docker compose -f docker-compose.standalone.yml up --force-recreate
```

After the cluster is built, you can access the turms-admin background management system through [http://localhost:6510](http://localhost:6510), and enter the account password (the default is `turms`). If the login is successful, it means that the Turms cluster is built successfully.

Note: AWS provides cost-effective `t4g` series EC2 instances, but because the t4g series instances use ARM processors, many applications cannot run on this type of EC2 instances, such as the images provided by `bitnami`. run on an instance of this class. ~~If you want to run `docker-compose.standalone.yml` on an ARM processor, you need to execute the following instructions first, compile and install the Loki plugin locally, and then run `docker-compose.standalone.yml`~ ~ Because `Loki` itself has a critical level bug (https://github.com/grafana/loki/issues/2361), that is, when the log cannot be delivered to the Loki server, it directly freezes our service, so we temporarily The Loki service has been removed, you don't need to execute the following commands, you can also run `docker-compose.standalone.yml` directly:

```bash
# Install Go
sudo add-apt-repository ppa:longsleep/golang-backports
sudo apt update
sudo apt install golang-go

# Install Loki
sudo docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions

# Build and Enable Loki
git clone https://github.com/grafana/loki.git
cd loki
git checkout "tags/v2.4.1" -b setup
sudo GOOS=linux GOARCH=arm GOARM=7 go build ./clients/cmd/docker-driver
# Replace "<ALPHA_NUMERIC_FOLDER>" with the real path on your machine
sudo mv docker-driver /var/lib/docker/plugins/<ALPHA_NUMERIC_FOLDER>/rootfs/bin
sudo docker plugin enable loki
```

Notes:

* With `--profile monitoring` (`docker compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate`), you can also automatically build Prometheus and Grafana servers.
* The Turms server uses the production environment configuration by default, and will not print logs to the console, only log files, so you cannot view the running logs of the Turms server through `docker logs <TURMS_CONTAINER_ID>`. To facilitate troubleshooting, you can set the environment variable to `ENV=dev` when developing and testing locally, and then start `docker-compose.standalone.yml` again. In the dev environment, Turms will print logs to the console, and automatically generate fake data for testing, and simulate real client TCP connections and requests
* If you pass the above command, `docker-compose.standalone.yml` cannot be started. Then make sure that the `Docker` version of the server is `20.x.x` and the `Docker Compose` plug-in version is `2.x.x`,
* The Playground environment and website of Turms are automatically built every time through the command `ENV=dev, demo docker compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate -d`

#### Based on Terraform and Docker Compose

(Because Turms does not currently provide packaged images, it is still necessary to use Docker Compose for environment construction)

This solution is based on the above-mentioned Docker Compose solution, and introduces the Terraform module customized by Turms to help users automatically open and configure VPC, switches, security groups, and stand-alone ECS preemptive instances. On this ECS, Terraform will install services such as Docker, Docker Compose, and Turms through the user-data system initialization script, and finally start the Turms stand-alone cluster.

The specific operation commands are as follows:

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms/terraform/alicloud/playground
export ALICLOUD_ACCESS_KEY=<your_access_key>
export ALICLOUD_SECRET_KEY=<your_secret_key>
terraform init
terraform apply
```

After the `terraform apply` command is executed, wait for about 3 to 15 minutes (Aliyun ECS pulls the ghcr image very slowly), and then visit `http://public IP:6510` (the public IP can be controlled by viewing If you can access the turms-admin background management system successfully, it means the setup is successful.

Note: This solution requires the purchase and use of cloud services, and the cost depends on the running time of ECS. Under the default configuration, about 0.1 yuan per hour (the price of preempting instances may fluctuate at any time, so please refer to the price list of cloud services for specific prices)

### Cloud-based Cluster Environment

Applicable scenarios: There are requirements for disaster recovery, elastic expansion, cross-regional deployment, and load balancing. The various capabilities provided by this solution are directly linked to the construction cost, so you usually need to modify the default Terraform module configuration to ensure that the configuration can meet your needs and the construction and operation and maintenance costs are the lowest.

The specific operation commands are as follows:

```bash
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms/terraform/alicloud/cluster
export ALICLOUD_ACCESS_KEY=<your_access_key>
export ALICLOUD_SECRET_KEY=<your_secret_key>
terraform init
terraform apply
```

The Terraform module will deploy cloud services in accordance with the conventional intra-city disaster recovery deployment scheme for Internet applications. Specifically include:

1. Set up a `VPC` in a region, and open 2 `switches` under the VPC, representing two `availability zones`
2. A set of `MongoDB Fragmented Cluster Service` is deployed by default in the above two availability zones to achieve disaster recovery in the same city
3. A set of `Redis service` is deployed by default in an availability zone
4. In order to receive the external network traffic sent to Turms ECS, open `SLB service`
5. In order to realize the external network access of Turms ECS, enable `NAT service`
6. Build respective `security groups` for turms-gateway, turms-service and turms-admin servers
7. For the turms-gateway server, open an `ECS instance` without public network bandwidth (the default number is 1). Realize the initialization and execution of the turms-gateway service through user-data, and bind with the above-mentioned SLB, NAT, security group, MongoDB, and Redis services
8. For the turms-service server, open an `ECS instance` without public network bandwidth (the default number is 1). Realize turms-service service initialization and execution through user-data, and bind with the above SLB, NAT, security group, MongoDB, Redis services
9. For the turms-admin server, open an `ECS instance` without public network bandwidth (the default number is 1). Realize the initialization and execution of the turms-admin service through user-data, and bind it to the above-mentioned security group service

Since then, the entire Turms basic cluster has been built (such as log analysis services will be provided in the future). For more implementation details, please refer to the specific Terraform module configuration in the `turms/terraform/alicloud/cluster` directory

## Manually Build and Start

Applicable scenarios: general purpose, no special restrictions. But generally only suitable for small-scale manual deployment.

If your network is smooth, it will take about 10-30 minutes to complete all the following operations for the first time. When you are proficient, you can complete the deployment of a whole set of clusters in 1 to 3 minutes.

1. MongoDB cluster construction (for business data storage, service discovery, configuration management)

    - Download and install [MongoDB](https://www.mongodb.com/download-center/community) (because the Turms server needs to use a fragmented cluster that supports distributed transactions, the minimum version of MongoDB is required to be 4.2. Recommended Users use the latest stable version). Take RHEL/CentOS as an example (for details, please refer to: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat):

      ```bash
      cat <<EOF> /etc/yum.repos.d/mongodb-org-6.0.repo
      [mongodb-org-6.0]
      name=MongoDB Repository
      baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/6.0/x86_64/
      gpgcheck=1
      enabled=1
      gpgkey=https://www.mongodb.org/static/pgp/server-6.0.asc
      EOF
      yum install -y mongodb-org
      ```

    - Build a MongoDB server shard cluster. Take the tool [mtools/mlaunch](https://github.com/rueckstiess/mtools) as an example:

      (For more instructions provided by mlaunch, readers can refer to: [mlaunch document](https://rueckstiess.github.io/mtools/mlaunch))

      ```bash
      pip3 install mtools [mlaunch]
      mlaunch init --replicaset --sharded 1 --nodes 1 --config 1 --hostname localhost --port 27017 --mongos 1
      ```

      Notice:

        * If you encounter an error similar to `error: Microsoft Visual C++ 14.0 or greater is required. Get it with "Microsoft C++ Build Tools"` when executing the `pip3 install mtools[mlaunch]` command in the Windows environment, you need to first Under https://visualstudio.microsoft.com/downloads page, download `Visual Studio Installer`, install `MSVC build tools` through it, and then execute `pip3 install mtools[mlaunch]` command.
        * Please make sure that the MongoDB server is running normally, otherwise the Turms server will throw a `MongoSocketOpenException` when it starts.

    2. Download, install and start the Redis server (for user status management and "nearby users"). Take RHEL/CentOS as an example:

       ```bash
       yum install epel-release
       yum update
       yum install redis
       systemctl start redis
       systemctl enable redis
       ```

       For the Windows platform, you can download the Windows version from [tporadowski/redis](https://github.com/tporadowski/redis/releases) for local development and testing.

3. Turms cluster construction

   Solution 1: Pull the Docker image of the Turms server and run:

    ```bash
    # Pull and run images
    docker run -p 6510:6510 ghcr.io/turms-im/turms-admin
    docker run -p 7510:7510 -p 8510:8510 ghcr.io/turms-im/turms-service
    docker run --ulimit nofile=102400:102400 -p 7510:7510 -p 9510:9510 -p 10510:10510 -p 11510:11510 -p 12510:12510 ghcr.io/turms-im/turms-gateway
    ```

   In addition, you can use custom `application.yaml` and `jvm.options` by volume mounting. For example, configure `-v /your-custom-config-dir:/opt/turms/turms/config`.

   Solution 2: ~~Download and decompress the [Turms server](https://github.com/turms-im/turms/releases) compressed package~~ (since v.0.10.0 has not been released on the release page, this scheme is not available at the moment), run according to the following steps:

    - (If you install the default configuration of both MongoDB and Redis locally, you can skip this step) Configure config/jvm.options, config/application.yaml according to your needs (you can configure Turms custom configuration here parameters, and you can also configure multiple MongoDB or mongos server addresses here. For details, please refer to: https://docs.mongodb.com/manual/reference/connection-string).

    - (Ansible is recommended) On all systems that need to run the Turms server, run the bin/turms script (the default is executed as a Thin package, if you need to execute it as a Fat package, please add the `-f` parameter when executing the script, Such as: `sh run.sh -f`. Then run the turms-gateway server. The turms-gateway and turms-service servers will automatically find other server nodes through MongoDB (as a service registry), so the Turms cluster start working.

   Solution 3: Clone the source code of the Turms warehouse, and run the turms-gateway and turms-service servers directly through the IDE. (Reference command: `git clone --depth 1 https://github.com/turms-im/turms.git`)

**Notes:**

* When the turms-service server is started, it will automatically detect whether there is a super administrator account with the role of `ROOT` and the account of `turms` in the database. If it does not exist, the turms-service server will automatically create a role with `ROOT`, name `turms` and password `turms.security.password.initial-root-password` (default: `turms`) Administrator account. In a production environment, please remember to change the default password.
* The above operations are mainly for your first experience of using Turms clusters. If you need to deploy Turms in a production environment, please be sure to refer to the Wiki manual to understand the meaning of various configuration parameters and customize your own with minimal resource consumption. Business needs and business mix.

## The general process of Turms server startup and shutdown

### Start the process

1. Connect and verify mongos and Redis server.
2. Check whether MongoDB has created a table. If the table has already been built, skip this step. If not, proceed: create tables, add indexes, add shard keys, and add Zones for separate storage of hot and cold data. If MongoDB's fake data is enabled, turms-service will automatically generate fake data to MongoDB for development and testing.
3. For the turms-service server, it will detect whether there is already a super administrator account with the role `ROOT` and the account `turms` in MongoDB. If it does not exist, an administrator account with role `ROOT`, name `turms` and password `turms.security.password.initial-root-password` (default: `turms`) will be created for MongoDB.
4. Register the local Node node to the service registration center. If the registration is successful, pull and apply the global configuration of the cluster, and build an RPC server to receive RPC client connections. If it fails, throw an exception and exit the process.
5. Open the Admin HTTP server to receive admin API requests. In addition, for turms-gateway, the gateway server (such as TCP/WebSocket) must be opened to receive client connections and requests.
6. For turms-gateway, if the Fake client is enabled, a real client connection is generated and a real client request is randomly sent (random request type, random request parameters) for development and testing.

At this point, the server is started.

### Shutdown Process

(for turms-gateway)
1. Deny new client network connections and client requests.
2. Close the fake clients and close the established client sessions.
3. Shut down the servers that connects to TCP, UDP, or WebSocket clients and the HTTP admin API server.

(for turms-gateway and turms-service)
4. Turn off the blocklist synchronization mechanism.
5. Close cluster services (such as the connection between RPC nodes, service registration and discovery service).
6. Turn off the plugin mechanism.
7. After sending requests to Redis and MongoDB, close the network connections from Turms server to Redis and MongoDB.
8. After flushing all logs, close the log service.

At this point, the server shutdown is complete.