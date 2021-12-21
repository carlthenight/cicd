pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId: '5bb248b0-3562-4ac8-8d99-60df393e0248',
                    url: 'https://github.com/carlthenight/cicd.git']]])
            }
        }
    }
}