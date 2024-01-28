####################################################################
# Stage 0 : BUILD
####################################################################
# Do NOT use alpine because if we use alpine:
# 1. We need to try which phython packages we use and install them one by one
# 2. We need to install python packages in every build
# 3. "node:20.9.0-slim" is small and can make life easier
FROM node:20.9.0-slim AS builder
COPY . .

# build in China
#RUN cd ./turms-admin \
#    && npm i --force --registry=https://registry.npm.taobao.org \
#    && npm run build

# FIXME: We don't use "--no-optional" as a workaround
# to fix the error "Cannot find module @rollup/rollup-linux-x64-gnu on Docker container".
# Wait for https://github.com/vitejs/vite/discussions/15532 to be fixed.
RUN cd ./turms-admin \
    && npm i --force \
    && npm run build

####################################################################
# Stage 1 : RUN
####################################################################
FROM nginx:1.24.0-alpine
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