
echo %1

mvn clean compile package

docker build -t sagarg1990/crawler .

docker push sagarg1990/crawler:%1

kubectl delete deployment crawler

kubectl run crawler --image=sagarg1990/crawler:%1 --po
rt 8080


kubectl expose deployment crawler  --type=LoadBalancer --port 80 --target-port 8080

kubectl get service
