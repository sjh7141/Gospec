#!/bin/bash

docker build -t gospec .

docker run -d -p 8282:8282 -v /home/ubuntu/docker:/tmp gospec
