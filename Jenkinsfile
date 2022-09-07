pipeline {
    agent any
    environment {
        dockerHome = tool 'myDocker'
        mavenHome = tool 'myMaven'
        PATH = "$dockerHome/bin:$mavenhome/bin:$PATH"
    }
    stages {
        stage('Checkout'){
            steps {
                echo "PATH - $PATH"
                echo "Build Id - $env.BUILD_ID"
                echo "Build Tag - $env.BUILD_TAG"
                echo "Build Name - $env.BUILD_NAME"
                echo "Build URL - $env.BUILD_URL"
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                //docker bulid -t avikpanja/user-management:$env.BUILD_TAG
                script {
                    dockerImage = docker.build("avikpanja/user-management:${env.BUILD_TAG}")
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('', 'dockerHub') {
                        dockerImage.push();
                        dockerImage.push('latest');
                    }
                }
            }
        }
        
    }
}
