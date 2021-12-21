pipeline {
    agent any

    stages {
        // stage('Checkout') {
        //     steps {
        //         checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        //             userRemoteConfigs: [[credentialsId: '5bb248b0-3562-4ac8-8d99-60df393e0248',
        //             url: 'https://github.com/carlthenight/cicd.git']]])
        //     }
        // }
        stage('Build'){
            steps{
                //构建Dockerfile定义的镜像
                sh "docker build -t go-app-img ."
                //使用yaml创建k3d集群
                sh "k3d cluster create go -c ./cluster_init.yaml"
                //查看nodes状况,是否创建集群成功
                sh "kubectl get nodes"
                //从docker导入镜像到k3d集群中
                sh "k3d image import -c go-cluster go-gin-img"
                //kubectl创建deployment
                sh "kubectl create -f deployment.yaml"


            }
        }
    }
}