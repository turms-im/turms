@echo off
mongo --port 29510 --eval "rs.initiate({_id:'rs0',members:[{_id:0,host:'localhost:29510','priority':1},{_id:1,host:'localhost:29511'}]});"