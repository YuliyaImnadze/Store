# Собрать образ
docker build -t store-app:latest .

# Тегировать образ для локального Registry
docker tag store-app:latest localhost:5000/store-app:latest

# Загрузить образ в Registry
docker push localhost:5000/store-app:latest

# Запуск Registry
docker run -d -p 5000:5000 --name registry registry:2

# Развернуть Postgres
helm install my-postgres bitnami/postgresql --set auth.username=postgres --set auth.password=postgres --set auth.database=Store

# Развернуть Redis
helm install my-redis bitnami/redis --set auth.enabled=false

# Развернуть приложение
helm install store-app ./store-app

# Обновление после внесения изменений в деплое
helm upgrade store-app ./store-app