pipeline {
    agent any

    stages {

        stage('Pre Work') {
            steps {
                //删除k3d集群
                sh "k3d cluster delete go"
                sh "rm -rf ./*"
            }
        }

        stage('git clone') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId: '5bb248b0-3562-4ac8-8d99-60df393e0248',
                    url: 'https://gitee.com/carlthenight/cicd.git']]])
            }
        }
        stage('Build'){
            steps{
                //构建Dockerfile定义的镜像
                sh "docker build -t go-gin-img ."
                //使用yaml创建k3d集群
                sh "k3d cluster create -c ./cluster_init.yaml"
                //查看nodes状况,是否创建集群成功
                sh "kubectl get nodes"
                       
            }
        }
        stage('Deploy'){
            steps{
                //从docker导入镜像到k3d集群中,因为没有做私有镜像仓库,只能手动导入
                sh "k3d image import -c go go-gin-img"
                //kubectl创建deployment
                sh "kubectl create -f deployment.yaml"
                //查看deployment
                sh "kubectl get deployments"
                //查看pods
                sh "kubectl get pods"
                //通过LoadBalancer方式暴露服务,集群只开了一个 `8081:80800@loadbalancer`
                sh "kubectl expose deployment my-go-app --name=my-go-svc --target-port=8080 --type=LoadBalancer" 
            }
        }
        stage('Test'){
            steps{
                echo "do TEST here!" 
            }
        }
    }
}