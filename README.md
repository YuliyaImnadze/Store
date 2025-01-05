#### Собрать образ
docker build -t store-app:latest .

#### Тегировать образ для локального Registry
docker tag store-app:latest localhost:5000/store-app:latest

#### Загрузить образ в Registry
docker push localhost:5000/store-app:latest

#### Запуск Registry
docker run -d -p 5000:5000 --name registry registry:2

#### Добавить репозитории Helm для официальных чартов:
helm repo add stable https://charts.helm.sh/stable
helm repo update

#### Развернуть Postgres (если не включать в Chart.yaml dependencies)
helm install my-postgres bitnami/postgresql --set auth.username=postgres --set auth.password=postgres --set auth.database=Store

#### Развернуть Redis (если не включать в Chart.yaml dependencies)
helm install my-redis bitnami/redis --set auth.enabled=false

#### Создание нового Helm-чарта:
helm create store-app

#### Развернуть приложение
helm install store-app ./store-app

#### Проверка шаблона
helm lint ./store-app

#### Обновление после внесения изменений в деплое
helm upgrade store-app ./store-app

#### Обновление зависимостей после добавления их в Chart.yaml dependencies
helm dependency update ./store-app

#### Удалить приложение из k8s
helm uninstall store-app

#### Удалить PostgreSQL и Redis (если они запускались отдельными командами)
helm uninstall my-postgres
helm uninstall my-redis

#### Установить NGINX Ingress Controller
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace

#### Проверить, что NGINX Ingress Controller установлен
kubectl get pods -n ingress-nginx

#### Установить Prometheus Operator
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update
helm install prometheus-operator prometheus-community/kube-prometheus-stack --namespace default --set prometheus-node-exporter.enabled=false
