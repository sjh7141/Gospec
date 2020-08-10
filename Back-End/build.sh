#!/bin/bash

docker build -t gospec .

docker run -d -p 8282:8181 gospec
