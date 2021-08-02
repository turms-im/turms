#!/bin/bash

apt-get update

# Set up Docker repository
apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo \
    "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
# Install Docker Engine
apt-get update
apt-get install -y docker-ce docker-ce-cli containerd.io

systemctl start docker
systemctl enable docker

# Install docker-compose
DOCKER_COMPOSE_URL="https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)"
if [ "${USE_CHINA_MIRROR}" == "true" ]; then
  DOCKER_COMPOSE_URL="https://get.daocloud.io/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)"
fi
curl -L "$DOCKER_COMPOSE_URL" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

# Install loki-docker-driver
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions

# Install git, wget, and unzip
apt-get install -y git unzip wget

# Set environment
cat > /etc/security/limits.conf << EOF
*         hard    nofile      100000
*         soft    nofile      100000
root      hard    nofile      100000
root      soft    nofile      100000
EOF

# Clone Turms project
git clone --depth 1 https://github.com/turms-im/turms.git /srv/git

# Run Turms Playground
docker-compose -f /srv/git/turms/docker-compose.standalone.yml --profile monitoring up --force-recreate -d