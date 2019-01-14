
call mvn clean package -Dmaven.test.skip=true

docker build -t=registry.cn-hangzhou.aliyuncs.com/cvi/pay-api .
docker push registry.cn-hangzhou.aliyuncs.com/cvi/pay-api



