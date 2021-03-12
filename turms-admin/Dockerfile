####################################################################
# Stage 0 : BUILD
####################################################################
FROM node:14-alpine as builder
WORKDIR app
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
FROM node:14-alpine
WORKDIR opt/turms/turms-admin
COPY --from=builder /app/dist ./dist
EXPOSE 6510

CMD [ "node", "run", "start" ]