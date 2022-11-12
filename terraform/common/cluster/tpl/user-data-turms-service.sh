#!/bin/bash

sudo apt-get update

# Set up Docker repository
sudo apt-get install -y \
  ca-certificates \
  curl \
  gnupg \
  lsb-release
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list >/dev/null

# Install Docker Engine
sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli containerd.io

sudo systemctl start docker
sudo systemctl enable docker

# Set environment
sudo bash -c 'cat > /etc/security/limits.conf' << EOF
*         hard    nofile      100000
*         soft    nofile      100000
root      hard    nofile      100000
root      soft    nofile      100000
EOF

sudo bash -c 'cat >> /etc/sysctl.conf' << EOF
fs.file-max = 1629424

vm.swappiness = 10

net.ipv4.tcp_max_syn_backlog = 65536
net.core.somaxconn = 65536
net.ipv4.tcp_syncookies = 1
net.ipv4.tcp_no_metrics_save = 1
net.ipv4.tcp_retries2 = 10
net.ipv4.tcp_syn_retries = 3
net.ipv4.tcp_synack_retries = 5
net.ipv4.tcp_sack = 1
net.ipv4.tcp_abort_on_overflow = 0
net.ipv4.tcp_max_orphans = 65536
net.ipv4.tcp_tw_reuse = 1
net.ipv4.tcp_timestamps = 1
net.ipv4.tcp_max_tw_buckets = 65536
net.ipv4.tcp_fin_timeout = 30
net.ipv4.tcp_fastopen = 3
net.core.netdev_max_backlog = 65536
net.core.rmem_max = 16777216
net.core.wmem_max = 16777216
net.ipv4.tcp_rmem = 4096 87380 16777216
net.ipv4.tcp_wmem = 4096 87380 16777216
net.ipv4.tcp_moderate_rcvbuf = 1
net.ipv4.tcp_window_scaling = 1
EOF
sudo sysctl -p

# Run turms-service
HOST=$(hostname -i)
sudo docker pull ghcr.io/turms-im/turms-service:latest
sudo docker run -d --name turms-service --ulimit nofile=100000 \
  --memory-swappiness=0 \
  -p 7510:7510 -p 8510:8510 \
  --health-cmd="curl -I --silent $${HOST}:8510/health || exit 1" \
  --health-interval=5s \
  --health-timeout=5s \
  --health-retries=3 \
  --health-start-period=60s \
  -e TURMS_SERVICE_JVM_OPTS="
  -Dspring.profiles.active=${PROFILE}
  -Dturms.cluster.connection.server.port-auto-increment=false
  -Dturms.cluster.discovery.address.advertise-strategy=advertise_address
  -Dturms.cluster.discovery.address.advertise-host=$${HOST}
  -Dturms.cluster.shared-config.mongo.uri=${CONFIG_MONGODB_URI}
  -Dturms.service.mongo.admin.uri=${ADMIN_MONGODB_URI}
  -Dturms.service.mongo.user.uri=${USER_MONGODB_URI}
  -Dturms.service.mongo.group.uri=${GROUP_MONGODB_URI}
  -Dturms.service.mongo.conversation.uri=${CONVERSATION_MONGODB_URI}
  -Dturms.service.mongo.message.uri=${MESSAGE_MONGODB_URI}
  -Dturms.service.redis.session.uri-list[0]=${SESSION_REDIS_URI}
  -Dturms.service.redis.location.uri-list[0]=${LOCATION_REDIS_URI}
  -Dturms.service.redis.ip-blocklist.uri=${IP_BLOCKLIST_REDIS_URI}
  -Dturms.service.redis.user-blocklist.uri=${USER_BLOCKLIST_REDIS_URI}
  ${CUSTOM_JVM_OPTS}" \
  ghcr.io/turms-im/turms-service