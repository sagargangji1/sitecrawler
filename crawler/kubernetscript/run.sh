
mvn clean compile package

//to remove unused Images
docker system prune -a

docker build -t sagarg1990/crawler .

docker push sagarg1990/crawler:$1

kubectl delete deployment crawler

kubectl run crawler --image=sagarg1990/crawler:$1 --port 8080

//for First Time only 
kubectl expose deployment crawler  --type=LoadBalancer --port 80 --target-port 8080

kubectl get service
