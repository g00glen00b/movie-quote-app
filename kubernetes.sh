#!/usr/bin/env bash
kubectl create configmap nginx-config --from-file=./config/nginx.conf
kubectl create configmap prometheus-config --from-file=./config/prometheus.yml
kubectl apply -f ./kubernetes/movie-quote-service.yml
kubectl apply -f ./kubernetes/movie-quote-consumer.yml
kubectl apply -f ./kubernetes/movie-quote-monitoring.yml
