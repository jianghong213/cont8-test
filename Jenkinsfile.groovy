#!/usr/bin/env groovy
// 镜像仓库地址需要时打开
// 使用需要下载pipline
def registry = "xxx"
def project = "max"
def app_name = "java-demo"
def image_name_java = "springboot"
def image_name_node = "node"
def git_address = "https://github.com/Crazyorchid/cont8-test"
pipeline {
    agent any
    stages {
        stage('拉取代码'){
            steps {
            
             checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'Crazyorchid', url: 'https://github.com/jianghong213/cont8-test']]])
            // 拉取代码
            }
        }

        stage('代码编译'){
           steps {
             sh """
                pwd
                ls
                cd server
                mvn clean package -Dmaven.test.skip=true
                # 开始编译nodejs代码  这里是vue
                cd ../client 
                npm install
                npm run build
                """ 
           }
        }
               //  JAVA_HOME=/usr/local/jdk
               //  PATH=$JAVA_HOME/bin:/usr/local/maven/bin:$PATH

        stage('构建镜像'){
           steps {
             sh """
                  docker build -t ${image_name_java} .
                  docker build -f Dockerfile-node -t ${image_name_node} .
                """
               // cleanWs(patterns: [[pattern: 'nope_modules', type: 'EXCLUDE']])
                }
           } 
        }
         stage('部署到Docker'){
           steps {
              sh """
              REPOSITORY=${image_name}
              docker rm -f tomcat-java-demo |true
              docker run -dit --name java-spring-boot -p 88:8080 springboot:latest
              """
            }
        } 
    }
