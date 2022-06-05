# Spring Boot - Back End API

This project was built for an assignment. It is to be run as a micro service on docker kubernetes environment.

## Requirements
To run this back-end API service on your machine, you have to install the following softwares:
* Git/Git Bash : for cloning source codes from the github.
* Apache Maven : for building and compiling Java project.
* Docker and Kubernetes : for deploying and running your application.

## Technologies
This application was built from the following technologies:
* Java Spring Boot
* Spring Security with JWT
* H2 Database (In-memory database)
* Hibernate JPA (ORM - Object-Relational Mapping)

## Let's Start
### Step 1: Cloning Project
On your machine, open the terminal or git bash and then run this command to clone the project from the git hub:
```
git clone https://github.com/sosereyboth/backend-api.git
```
### Step 2: Building and Compiling Maven Project
When cloning project completed, on the terminal or git bash screen, navigate to the downloaded folder named "backend-api" and start building the project.
```
cd backend-api

mvn clean package 
```
After the process completed, a "jar" file was produced in the "target" directory in the project root folder. This .jar file is going to be used to deploy and run on the docker kubernetes.
### Step 3: Building Docker Image
Now it's time to build a docker image from the .jar file and store the image on docker repository. To do it, run the following command on the same terminal screen:
```
docker build -t backend-api:latest .
```
When the process done, you can verify that image by running this command:
```
docker images
```
Then you'll see the image named "backend-api" with the tag "latest". We will use this image to deploy on the kubernetes shortly.

### Step 3: Deploying on Kubernetes
Under the "backend-api" folder on your terminal screen, you can view the file named "deploy.yaml". This file is the Kubernetes deployment script. To deploy our application on the Kubernetes, run the following command line on your terminal screen:
```
kubectl apply -f deploy.yaml
```
Now you can verify your application running on the kubernetes using this command:
```
kubectl get all -n manulife
```
> Then you will see the service named "service/backend-api-svc" which running on the port "30002". This port number is accessible from the outside of Kubernetes cluster. this means you can access your back-end API service from the client browser or postman through this port "30002".
Now you can test this URL on the local machine to make sure it works:
> ```
> http://localhost:30002/hello
> ```

