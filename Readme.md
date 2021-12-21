# :computer:工程实践---后端作业(2021年12月)

## :package:题目：使用 Jenkins 构建服务的镜像，并将其发布到 Kubernetes 集群，要求整个 CICD 过程连贯，能够一键触发或提交代码之后自动触发执行。

## :clipboard:要求：
:one: 选择一个自己熟悉的后端服务项目， 也可以使用 https://github.com/shaowenchen/gin-hello-world 。  
:two: 选择 Pipeline 类型的流水线，使用 Jenkinsfile 文件定义流水线。  
:three: 应用在 Kubernetes 集群的相关对象，使用 Yaml 文件显式定义。  
:four: 根据要求的处理流程，画出各个组件之间的数据流关系，给出每个关键步骤执行成功时的日志或截图。  
:five: 配置和代码放在同一个地方（推荐 Git 仓库）进行版本管理。

## :pencil2:完成情况:

:white_check_mark:**使用 Yaml 文件显式定义k8s集群。**  
:white_check_mark:**构建服务镜像。Dockerfile。**  
:white_check_mark:**部署服务到k8s集群。**  
:white_check_mark:**使用Jenkins file 文件定义流水线。**  
:white_check_mark:**画出各个组件之间的数据流关系。**  
:white_check_mark:**每个关键步骤执行成功时的日志或截图。**  
:white_check_mark:**定时触发执行。(使用 :alarm_clock: Poll SCM)**  

## :wrench:使用工具:
- [k3d](https://k3d.io/)- k3d is a lightweight wrapper to run [k3s](https://github.com/rancher/k3s)  in docker.
- [kubectl](https://kubernetes.io/zh/docs/reference/kubectl/overview/) - to interact with the Kubernetes cluster.
- [docker](https://docs.docker.com/get-docker/) - to be able to use k3d at all
  - Note: k3d v5.x.x requires at least Docker v20.10.4 to work properly
  
## :rocket:收获:
 ![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=docker&logoColor=white)
 ![Jenkins](https://img.shields.io/badge/-Jenkins-orange?style=flat-square&logo=Jenkins&logoColor=white)
 ![Kubernetes](https://img.shields.io/badge/-Kubernetes-326CE5?style=flat-square&logo=Kubernetes&logoColor=white) 
 ![git](https://img.shields.io/badge/-Git-F05032?style=flat-square&logo=git&logoColor=white) 
 ![GitHub](https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github&logoColor=white) 
 ![k3d](https://img.shields.io/badge/-k3d-0075A8?style=flat-square&logo=k3d&logoColor=white) 

 ![Visual Studio Code](https://img.shields.io/badge/-Visual%20Studio%20Code-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white) 

   
:bookmark: Dockerfile文件的编写。  
:bookmark: Docker镜像的打包、使用、部署。  
:bookmark: Jenkins的Pipeline使用、Poll SCM轮询。  
:bookmark: 使用k3d进行k8s的集群模拟。  
:bookmark: 使用kubectl命令进行部署、暴露服务。   
:bookmark: 使用Yaml文件显示定义k3d集群和deployment。   
:bookmark: 了解CICD过程中的概念。

## :bar_chart:可优化：
1. 因为Docker没有部署私有仓库，所以使用kubectl命令部署时，需要将制作的Docker镜像手动导入到k3d的Node中，即pipeline中的:  
 ```groovy 
 sh "k3d image import -c go go-gin-img"
 ```
2. Jenkins没有部署到有公网ip的服务器中，或者使用内网穿透工具，所以webhook暂时无法使用，只能使用 poll SCM 定时轮询查看github有没有更新代码。 
3. :joy:...还有很多可以优化，流程写得太死。 

## :books:参考：
1. 如何在本地快速启动一个 K8S 集群 - https://zhuanlan.zhihu.com/p/357907926
2. Kubernetes入门实践--部署运行Go项目 - https://zhuanlan.zhihu.com/p/152394424
3. jenkins + pipeline构建自动化部署 - https://www.cnblogs.com/shenh/p/8963688.html
4. Kubernetes Docs - https://kubernetes.io/zh/docs/home/ 
5. K3D Docs - https://k3d.io/v5.2.2/usage/configfile/