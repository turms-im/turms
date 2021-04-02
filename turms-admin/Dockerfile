####################################################################
# Stage 0 : BUILD
####################################################################
FROM node:14-alpine as builder
COPY . .

# build in China
#RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
#    && apk add --no-cache python make g++ \
#    && npm install --no-optional --registry=https://registry.npm.taobao.org \
#    && npm run build

RUN apk add --no-cache python make g++ \
    && npm install --no-optional \
    && npm run build

####################################################################
# Stage 1 : RUN
####################################################################
FROM nginx:1.18-alpine
COPY --from=builder /dist /usr/share/nginx/html

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