####################################################################
# Stage 0 : BUILD
####################################################################
FROM node:16-alpine as builder
COPY . .

# build in China
#RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
#RUN apk add --no-cache python3 make g++ \
#    pkgconfig \
#    pixman-dev \
#    cairo-dev \
#    pango-dev \
#    jpeg-dev \
#    giflib-dev \
#    librsvg-dev \
#    && cd ./turms-admin \
#    && npm i --no-optional --force --registry=https://registry.npm.taobao.org \
#    && npm run build

# python3: required by node-gyp
# *-dev: required by node-canvas
RUN apk add --no-cache python3 make g++ \
    pkgconfig \
    pixman-dev \
    cairo-dev \
    pango-dev \
    jpeg-dev \
    giflib-dev \
    librsvg-dev \
    && cd ./turms-admin \
    && npm i --no-optional --force \
    && npm run build

####################################################################
# Stage 1 : RUN
####################################################################
FROM nginx:1.20-alpine
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