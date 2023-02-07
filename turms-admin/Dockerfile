####################################################################
# Stage 0 : BUILD
####################################################################
# Do NOT use alpine because if we use alpine:
# 1. We need to try which phython packages we use and install them one by one
# 2. We need to install python packages in every build
# 3. "node:18.13-slim" is small and can make life easier
FROM node:18.13-slim as builder
COPY . .

# build in China
#RUN cd ./turms-admin \
#    && npm i --no-optional --force --registry=https://registry.npm.taobao.org \
#    && npm run build

RUN cd ./turms-admin \
    && npm i --no-optional --force \
    && npm run build

####################################################################
# Stage 1 : RUN
####################################################################
FROM nginx:1.22-alpine
COPY --from=builder /turms-admin/dist /usr/share/nginx/html

RUN echo '                                                        \
server {                                                          \
    listen       6510;                                            \
    server_name  localhost;                                       \
    gzip on;                                                      \
    gzip_vary on;                                                 \
    gzip_types text/css application/javascript image/svg+xml;     \
                                                                  \
    location / {                                                  \
        root   /usr/share/nginx/html;                             \
        try_files $uri /index.html;                               \
    }                                                             \
}' > /etc/nginx/conf.d/default.conf

EXPOSE 6510

CMD ["nginx", "-g", "daemon off;"]