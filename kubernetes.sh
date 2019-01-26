#!/usr/bin/env bash
kubectl create configmap nginx-config --from-file=./config/nginx.conf
kubectl create configmap prometheus-config --from-file=./config/prometheus.yml
kubectl create -f ./kubernetes/movie-quote-service.yml
kubectl create -f ./kubernetes/movie-quote-consumer.yml
