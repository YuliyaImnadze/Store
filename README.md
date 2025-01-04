#### Собрать образ
docker build -t store-app:latest .

#### Тегировать образ для локального Registry
docker tag store-app:latest localhost:5000/store-app:latest

#### Загрузить образ в Registry
docker push localhost:5000/store-app:latest

#### Запуск Registry
docker run -d -p 5000:5000 --name registry registry:2

#### Развернуть Postgres (если не включать в Chart.yaml dependencies)
helm install my-postgres bitnami/postgresql --set auth.username=postgres --set auth.password=postgres --set auth.database=Store

#### Развернуть Redis (если не включать в Chart.yaml dependencies)
helm install my-redis bitnami/redis --set auth.enabled=false

#### Развернуть приложение
helm install store-app ./store-app

#### Проверка шаблона
helm lint ./store-app

#### Обновление после внесения изменений в деплое
helm upgrade store-app ./store-app

#### Обновление после внесения изменений в деплое
helm dependency update ./store-app

#### Удалить приложение из k8s
helm uninstall store-app

#### Удалить PostgreSQL и Redis (если они запускались отдельными командами)
helm uninstall my-postgres
helm uninstall my-redis
