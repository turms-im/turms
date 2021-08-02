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
    $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list >/dev/null

# Install Docker Engine
apt-get update
apt-get install -y docker-ce docker-ce-cli containerd.io

systemctl start docker
systemctl enable docker

# Run turms-admin
HOST=$(hostname -i)
docker pull ghcr.io/turms-im/turms-admin:latest
docker run -d --name turms-admin \
  -p 6510:6510 \
  --health-cmd="curl -I --silent $${HOST}:6510 || exit 1" \
  --health-interval=5s \
  --health-timeout=5s \
  --health-retries=3 \
  --health-start-period=10s \
  ghcr.io/turms-im/turms-admin
