def call(Map config) {

    echo "Deploying ${config.appName}"

    git branch: config.branch,
        url: config.gitUrl

    sh 'mvn clean package'

    sh """
        cp target/*.war ${config.tomcatPath}/${config.appName}.war
    """
}
