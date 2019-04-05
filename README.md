### Setup
1. Install Kubernetes and Minikube

    ```bash
    # For macOS
    brew install kubernetes-cli
    brew cask install virtualbox
    brew cask install minikube
    ```

2. Set up Minikube

    ```bash
    minikube config set memory 6144
    minikube config set cpus 2
    minikube config set vm-driver virtualbox
    minikube start
    minikube docker-env
    ```
3. Install Istio

    ```bash
    brew install kubernetes-helm
    kubectl create namespace istio-system
    helm template $ISTIO_RELEASE/install/kubernetes/helm/istio-init \
        --name istio-init \
        --namespace istio-system > kubefiles/istio-init.yml
    helm template $ISTIO_RELEASE/install/kubernetes/helm/istio/ \
        --name istio \
        --namespace istio-system \
        --set gateways.istio-ingressgateway.type=NodePort \
        --set gateways.istio-egressgateway.type=NodePort \
        --set mixer.telemetry.enabled=false \
        --set kiali.enabled=true > kubefiles/istio.yml
    kubectl apply -f kubefiles/istio-init.yml
    kubectl apply -f kubefiles/istio.yml
    ```
    
    
### Read more
I've also written some blogposts about this project:

1. Creating Docker containers for your Spring boot application
2. Starting a blank web project with Babel and Webpack
3. Deploying your static web files with Docker
4. Monitoring your Spring boot applications with Micrometer, Prometheus and Grafana
5. Monitoring nginx with Prometheus and Grafana
6. Setting up your application on Kubernetes
7. Checking your service dependencies with Istio
