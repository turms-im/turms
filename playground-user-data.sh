#!/bin/bash

yum upgrade -y

# Install yum-config-manager
yum install -y yum-utils

# Install docker
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install docker-ce docker-ce-cli containerd.io
#usermod -a -G docker ec2-user
systemctl start docker
systemctl enable docker

# Install docker-compose
curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

# Install loki-docker-driver
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions

# Install git, wget, and unzip
yum install -y git
yum install -y unzip
yum install -y wget

# Set environment
cat > /etc/security/limits.conf << EOF
*         hard    nofile      100000
*         soft    nofile      100000
root      hard    nofile      100000
root      soft    nofile      100000
EOF

# Clone Turms project
git clone https://github.com/turms-im/turms.git /srv/git

# Run Turms Playground
#ENV=dev docker-compose -f /srv/git/turms/docker-compose.standalone.yml --profile monitoring up --force-recreate