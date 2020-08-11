#!/bin/bash

docker build -t gospec .

docker run -p 8282:8282 gospec
