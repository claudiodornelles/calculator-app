# How to deploy the application
This application was developed to work as a cloud native application and automated in a Jenkins environment.
The deployment were seperated in three steps (Jobs) such as build, provision and deploy.
## Job 1 - Build
1. Start of by downloading Jenkins from the [official website](https://www.jenkins.io/download/)
2. In order to install Jenkins it's mandatory to have a Java runtime environment installed in your computer. You can have more information on that [here](https://www.jenkins.io/doc/book/installing/)
3. After installing Jenkins, access the URL `http://localhost:8080` to access its dashboard.
   1. If any problem occurs, and you do not have access to Jenkins dashboard, you can check if it is running by typing the following command in a terminal window `service jenkins status`
   2. You can use this command to perform manual tasks with jenkins `service jenkins { start | stop | status | restart | force-reload }`
   3. Jenkins uses by default the port 8080, you can change that within the file `/etc/default/Jenkins`. Just change the property `HTTP_PORT`
4. It's recommended to chance the default admin password at this time.
5. Follow along Jenkins' configuration wizard and install the recommended plugins.
6. Now that you are at Jenkins dashboard, go to `Manage Jenkins > Manage Plugins > Available` and search for the plugin called `Artifactory Plugin` to install it.
7. You can go back to Jenkins dashboard and click `New Item` to create the first job.
   1. Enter the name `job1-build` for it.
   2. Choose `Pipeline` option and click `ok`
   3. Scroll down to `Pipeline` section and change the `Definition` field to `Pipeline script from SCM`
   4. Select `Git` and add the following repository URL `https://github.com/claudiodornelles/calculator-app.git`
   5. Scroll down to `Script Path`, type `job1/Jenkinsfile` and then click `Save`
8. Configure the publishing environment with JFrog Artifactory.
   1. Download JFrog Artifactory from the [official website](https://jfrog.com/download-jfrog-platform/) and follow the configuration tutorial from [here](https://www.jfrog.com/confluence/display/JFROG/Installing+Artifactory)
      1. If you have a Tomcat installed in your computer, make sure you do not have an environment variable called `CATALINA_HOME` configured. It might conflict with JFrog Artifactory installation, and it won't be able to run automatically.
   2. After installing Artifactory, run it in a terminal window with the following command `service artifactory <start|stop|check>`
   3. You can now access JFrog dashboard over `http://localhost:8082/ui` with the default credential `user: admin` and `password: password`
      1. It is recommended to change the admin password. (Take note of this, we will refer this as **_JFrog's admin credentials_** later on)
   4. After logging in, click on the gear icon at the top left menu and access `Repositories > Repositories`
   5. Click `Add Repositories > Local Repository` in the top right corner
   6. Choose Package Type as `Gradle`, give it the Repository Key as `calculator-app` and click `Save & Finish`
9. Last step is to configure Jenkins to have access to JFrog Artifactory
   1. Go back to Jenkins dashboard and click `Manage Jenkins > Configure System`
   2. Scroll down to JFrog section
   3. Click `Add JFrog Plataform Instance` to create a new JFrog instance
   4. Give it the Instance ID `jfrog-final-task` and type the access `URL http://localhost:8082`.
   5. Now type the **_JFrog's admin credentials_** to give it permissions and click `Test Connection` button.
   6. You should now see a message saying `Found JFrog Artifactory {version} at http://localhost:8082/artifactory`.
   7. If so, you can move on.
10. Go back to Jenkins dashboard and build Job 1.
## Job 2 - Provision
1. For this job you will need to have installed in your local machine both Packer and Docker. 
   1. You can download Packer from the official website [here](https://learn.hashicorp.com/tutorials/packer/get-started-install-cli)
   2. For downloading docker, please follow [this steps](https://docs.docker.com/engine/install/).
      1. After installing docker, you will need to run the command `sudo chmod 666 /var/run/docker.sock` in a terminal window in order to give it permission to run without a `sudo` command.
3. Go to Jenkins dashboard and click `New Item` to create job 2.
    1. Enter the name `job2-provision` for it.
    2. Choose `Pipeline` option and click `ok`
    3. Scroll down to `Pipeline` section and change the `Definition` field to `Pipeline script from SCM`
    4. Select `Git` and add the following repository URL `https://github.com/claudiodornelles/calculator-app.git`
    5. Scroll down to `Script Path`, type `job2/Jenkinsfile` and then click `Save`
4. For this job to be executed, you need to provide Dockerhub credentials and repository information to Jenkins.
   1. Go to Jenkins dashboard and click `Manage Jenkins > Manage Credentials > (global) > Add Credentials`
   2. Create a `Username with password` credential with your Dockerhub login information (If you do not have a Dockerhub account, create one [here](https://hub.docker.com/signup))
      1. Set this credential ID as `dockerhub-credentials`
   3. Create another credentials, now with kind `Secret text` and provide the Dockerhub repository name (If you do not have a repository in Dockerhub, create a new public one)
      1. NOTE: the `Secret` will be `yourDockerhubUsername/repositoryName` i.e: `claudiodornelles/tema-final-01`
      2. Set this credential ID as `dockerhub-repository-name`
5. 
## Job 3 - Deploy
