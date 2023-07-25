# Copyright 2018 The Kubernetes Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

.PHONY: all build

IMAGE_NAME=my-image/springboot-tools
IMAGE_VERSION=v1
VERSION=$(shell git rev-parse --short HEAD)

all: build

build:
	docker build --build-arg version=$(VERSION) -t $(IMAGE_NAME):$(IMAGE_VERSION) -f ./Dockerfile .

# 启动容器镜像
# docker run -d -p 8081:8081 --name my-springboot  my-image/springboot-tools:v1
