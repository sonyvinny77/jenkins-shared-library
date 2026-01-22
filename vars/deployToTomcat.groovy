def call(Map config) 
{
    pipeline 
    {
        agent any
        stages 
        {
            stage("Checkout - ${config.appName}") 
            {
                steps 
                {
                    git branch: 'main',
                        url: config.gitRepo
                }
            }
            stage("Build WAR - ${config.appName}") 
            {
                steps 
                {
                    sh 'mvn clean package'
                }
            }
            stage("Deploy to Tomcat - ${config.appName}") 
            {
                steps 
                {
                    sh """
                    cp target/*.war /opt/tomcat/webapps/${config.appName}.war
                    sudo systemctl start tomcat
                    """
                }
            }
        }
    }
}
