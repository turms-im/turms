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

# Install Docker engine and the compose plugin
sudo apt-get install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin

sudo systemctl start docker
sudo systemctl enable docker

# Install loki-docker-driver
sudo docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions

# Install git, wget, and unzip
sudo apt-get install -y git unzip wget

# Set environment
sudo bash -c 'cat > /etc/security/limits.conf' << EOF
*         hard    nofile      1048576
*         soft    nofile      1048576
root      hard    nofile      1048576
root      soft    nofile      1048576
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

# Clone Turms project
sudo git clone --depth 1 https://github.com/turms-im/turms.git /srv/git

# Run Turms Playground
sudo docker compose -f /srv/git/turms/docker-compose.standalone.yml --profile monitoring up --force-recreate -d
