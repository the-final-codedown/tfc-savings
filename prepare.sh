#!/bin/bash

mvn clean package

IMAGE=tfc/savings
VERSION=

docker build -t ${IMAGE} .
docker tag ${IMAGE} localhost:5000/${IMAGE}${VERSION}
docker push localhost:5000/${IMAGE}${VERSION}
