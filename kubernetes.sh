#!/usr/bin/env bash
kubectl create configmap nginx-config --from-file=./config/nginx.conf
kubectl create configmap prometheus-config --from-file=./config/prometheus.yml
#kubectl apply -f kubefiles/movie-quote-service.yml
#kubectl apply -f kubefiles/movie-quote-consumer.yml
#kubectl apply -f kubefiles/movie-quote-monitoring.yml
istioctl kube-inject -f kubefiles/movie-quote-service.yml | kubectl apply -f -
istioctl kube-inject -f kubefiles/movie-quote-consumer.yml | kubectl apply -f -
istioctl kube-inject -f kubefiles/movie-quote-monitoring.yml | kubectl apply -f -
