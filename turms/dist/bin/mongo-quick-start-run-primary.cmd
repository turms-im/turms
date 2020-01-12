@echo off
md temp\rs0-0 temp\rs0-1
mongod --replSet rs0 --port 29510 --dbpath temp\rs0-0